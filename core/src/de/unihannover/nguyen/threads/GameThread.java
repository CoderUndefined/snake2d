package de.unihannover.nguyen.threads;


import com.badlogic.gdx.Input;
import de.unihannover.nguyen.data.GameState;
import de.unihannover.nguyen.data.Coordinate;
import de.unihannover.nguyen.data.Direction;
import de.unihannover.nguyen.data.Player;

import java.util.LinkedList;

/**
 * Actual GameThread where all the game logic happens.
 */
public class GameThread extends Thread {

    boolean active = true;

    int SIZE_X = 32;
    int SIZE_Y = 18;
    Double speed = 200.0;

    GameState[][] map;
    Coordinate currentBeacon;

    Player player1;
    Player player2;


    /**
     * Basic constructor. Should be started with Thread.start().
     * In that case, first the initialize function is started which sets the Thread up
     * Then the run loop starts.
     */
    public GameThread() {
        super();
        initialize();
    }

    /**
     * Primary game loop.
     *
     * First try to move both snakes normally, with player inputs taken into accounts
     *
     * Then check for collision with other snakes or the game border
     * If that happens then the thread will be stopped and the game ends
     *
     * If that did not happen then check if a beacon has been collected
     * which will grow the snake by one segment that has eaten it.
     * Place a new beacon then.
     *
     * Speed up the game very slightly at every cycle.
     */
    @Override
    public void run() {

        // Thread "stops" if snake collision detected
        for(;;) {
            long time = System.currentTimeMillis();

            // Collision check
            boolean player1collision = false;
            boolean player1collected = false;
            boolean player2collision = false;
            boolean player2collected = false;

            int x1 = player1.getCurrentPosition().getX();
            int y1 = player1.getCurrentPosition().getY();
            int x1next;
            int y1next;

            // Handle player movement.
            if(player1.getPlayerDirection() == Direction.UP) {
                x1next = x1;
                y1next = y1-1;
            }
            else if(player1.getPlayerDirection() == Direction.DOWN) {
                x1next = x1;
                y1next = y1+1;
            }
            else if(player1.getPlayerDirection() == Direction.LEFT) {
                x1next = x1-1;
                y1next = y1;
            }
            else { // RIGHT
                x1next = x1+1;
                y1next = y1;
            }


            int x2 = player2.getCurrentPosition().getX();
            int y2 = player2.getCurrentPosition().getY();
            int x2next;
            int y2next;

            if(player2.getPlayerDirection() == Direction.UP) {
                x2next = x2;
                y2next = y2-1;
            }
            else if(player2.getPlayerDirection() == Direction.DOWN) {
                x2next = x2;
                y2next = y2+1;
            }
            else if(player2.getPlayerDirection() == Direction.LEFT) {
                x2next = x2-1;
                y2next = y2;
            }
            else { // RIGHT
                x2next = x2+1;
                y2next = y2;
            }
            // Movement parts ends here

            player1collected = collectionCheck(x1next, y1next);
            player2collected = collectionCheck(x2next, y2next);

            player1collision = collisionCheck( x1next, y1next,
                    player1collected, player2collected);
            player2collision = collisionCheck( x2next, y2next,
                    player1collected, player2collected);

            // head-on collisions
            if(x1next == x2next && y1next == y2next) {
                player1collision = true;
                player2collision = true;
            }


            // Case 1: Collision occurs/Game Over
            if(player1collision) {
                break;
            }
            if(player2collision) {
                break;
            }


            // Case 2: Beacon collected, snake gets longer, new beacon placed
            if(player1collected) {
                player1.getSnakeSegmentList().add(new Coordinate(x1next,y1next));
                removeBeacon();
                placeBeacon();
                map[x1next][y1next] = GameState.ONE;
            }
            else {
                player1.getSnakeSegmentList().add(new Coordinate(x1next,y1next));
                map[x1next][y1next] = GameState.ONE;
                player1.getSnakeSegmentList().removeFirst();
                map[player1.getTail().getX()][player1.getTail().getY()] = GameState.EMPTY;

            }

            if(player2collected) {
                player2.getSnakeSegmentList().add(new Coordinate(x2next,y2next));
                removeBeacon();
                placeBeacon();
                map[x2next][y2next] = GameState.TWO;
            }
            else {
                player2.getSnakeSegmentList().add(new Coordinate(x2next,y2next));
                map[x2next][y2next] = GameState.TWO;
                player2.getSnakeSegmentList().removeFirst();
                map[player2.getTail().getX()][player2.getTail().getY()] = GameState.EMPTY;
            }


            // Case 3: Else


            // Do speed up the game over time, but also put a limit so it wont get too fast
            speed = speed * 0.999;
            if(speed < 5.0) {
                speed = 5.0;
            }

            long tasktime = System.currentTimeMillis() - time;
            long lTime = Math.round(Math.floor(speed));
            long t = lTime - tasktime;
            if(t < 0) {
                System.out.println("SLOWDOWN DETECTED");
            }
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // wait for 100ms, including calculated time. Accelerate as time goes on

        }
        active = false;
    }

    // Take account of "tail"

    /**
     * Check if the snake has collided with the borders or with any player
     *
     * @param xNext next x coordinate of snake
     * @param yNext next y coordinate of snake
     * @param collected1 player1 has collected food
     * @param collected2 player2 has collected food
     * @return true if there was a collision otherwise false.
     */
    private boolean collisionCheck(int xNext, int yNext,
                                   boolean collected1, boolean collected2) {
        // Check if snake attempts to exit map.
        if(xNext < 0 || xNext >= SIZE_X || yNext < 0 || yNext >= SIZE_Y) {
            return true;
        }
        // Check collision against snakes
        else if(map[xNext][yNext] == GameState.ONE ||
                map[xNext][yNext] == GameState.TWO) {

            // counter check against tail
            int x1 = player1.getTail().getX();
            int y1 = player1.getTail().getY();
            int x2 = player2.getTail().getX();
            int y2 = player2.getTail().getY();

            if     (xNext == x1 && yNext == y1 && !collected1) {
                return false;
            }
            else if(xNext == x2 && yNext == y2 && !collected2) {
                return false;
            }
            return true;
        }
        else return false;
    }

    /**
     * Check if food was collected by a snake
     *
     * @param xNext next x coordinate of snake
     * @param yNext next y coordinate of snake
     * @return true if snake has collected food otherwise false
     */
    private boolean collectionCheck(int xNext, int yNext) {
        if(xNext < 0 || xNext >= SIZE_X || yNext < 0 || yNext >= SIZE_Y) {
            return false;
        }

        if(map[xNext][yNext] == GameState.BEACON) {
            return true;
        }
        else return false;
    }

    /**
     * Initialize the game map with 2 snakes of 8 length.
     */
    private void initialize() {

        // initialize map
        map = new GameState[SIZE_X][SIZE_Y];
        for(int y = 0; y < SIZE_Y; y++) {
            for(int x = 0; x < SIZE_X; x++) {
                map[x][y] = GameState.EMPTY;
            }
        }


        LinkedList<Coordinate> playerOneQueue = new LinkedList<>();

        // add oldest coordinate first, newest one last, IN THAT ORDER
        playerOneQueue.add(new Coordinate(0,4));
        playerOneQueue.add(new Coordinate(1,4));
        playerOneQueue.add(new Coordinate(2,4));
        playerOneQueue.add(new Coordinate(3,4));
        playerOneQueue.add(new Coordinate(4,4));
        playerOneQueue.add(new Coordinate(5,4));
        playerOneQueue.add(new Coordinate(6,4));
        playerOneQueue.add(new Coordinate(7,4));
        player1 = new Player(Direction.RIGHT, playerOneQueue);

        // put snake one
//        map[0][4] = GameState.ONE;
        map[1][4] = GameState.ONE;
        map[2][4] = GameState.ONE;
        map[3][4] = GameState.ONE;
        map[4][4] = GameState.ONE;
        map[5][4] = GameState.ONE;
        map[6][4] = GameState.ONE;
        map[7][4] = GameState.ONE;


        LinkedList<Coordinate> playerTwoQueue = new LinkedList<>();

        // add oldest coordinate first, newest one last, IN THAT ORDER
        playerTwoQueue.add(new Coordinate(31,14));
        playerTwoQueue.add(new Coordinate(30,14));
        playerTwoQueue.add(new Coordinate(29,14));
        playerTwoQueue.add(new Coordinate(28,14));
        playerTwoQueue.add(new Coordinate(27,14));
        playerTwoQueue.add(new Coordinate(26,14));
        playerTwoQueue.add(new Coordinate(25,14));
        playerTwoQueue.add(new Coordinate(24,14));
        player2 = new Player(Direction.LEFT, playerTwoQueue);

        // put snake two
//        map[31][14] = GameState.TWO;
        map[30][14] = GameState.TWO;
        map[29][14] = GameState.TWO;
        map[28][14] = GameState.TWO;
        map[27][14] = GameState.TWO;
        map[26][14] = GameState.TWO;
        map[25][14] = GameState.TWO;
        map[24][14] = GameState.TWO;


        // place beacon randomly
        placeBeacon();
    }

    /**
     * Randomly place a beacon on the Map. Make sure that spot is empty!
     */
    private void placeBeacon() {


        for(;;) {
            int total = SIZE_X * SIZE_Y;
            int coord = (int) Math.round(Math.floor(Math.random() * total));

            int x = coord / SIZE_Y;
            int y = coord % SIZE_Y;
//            System.out.println(coord+"=>"+x+"/"+y);

            if(map[x][y] == GameState.EMPTY) {
                map[x][y] = GameState.BEACON;
                currentBeacon = new Coordinate(x,y);
                break;
            }
        }

    }

    /**
     * Remove that beacon once its consumed
     */
    private void removeBeacon() {
        int x = currentBeacon.getX();
        int y = currentBeacon.getY();
        map[x][y] = GameState.EMPTY;
    }

    /**
     * Handle the input passed from the InputProcessor from the GameScreen
     * @param input user input as key press
     */
    public void handleInput(int input) {
        switch (input) {
            case Input.Keys.W:
//                System.out.println("W pressed");
                player1.steer(Direction.UP);
                break;
            case Input.Keys.S:
//                System.out.println("S pressed");
                player1.steer(Direction.DOWN);
                break;
            case Input.Keys.A:
//                System.out.println("A pressed");
                player1.steer(Direction.LEFT);
                break;
            case Input.Keys.D:
//                System.out.println("D pressed");
                player1.steer(Direction.RIGHT);
                break;

            case Input.Keys.UP:
//                System.out.println("UP");
                player2.steer(Direction.UP);
                break;
            case Input.Keys.DOWN:
//                System.out.println("DOWN");
                player2.steer(Direction.DOWN);
                break;
            case Input.Keys.LEFT:
//                System.out.println("LEFT");
                player2.steer(Direction.LEFT);
                break;
            case Input.Keys.RIGHT:
//                System.out.println("RIGHT");
                player2.steer(Direction.RIGHT);
                break;

            default:
                break;
        }
    }

    public GameState[][] getMap() {
        return map;
    }

    public int getSIZE_X() {
        return SIZE_X;
    }

    public int getSIZE_Y() {
        return SIZE_Y;
    }

    public boolean isActive() {
        return active;
    }
}


/*
 01234567890123456789012345678901
0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
2xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
3xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
411111111xxxxxxxxxxxxxxxxxxxxxxxx
5xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
6xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
7xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
8xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
9xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
1xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
2xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
3xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
4xxxxxxxxxxxxxxxxxxxxxxxx22222222
5xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
6xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
7xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
8xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 */
