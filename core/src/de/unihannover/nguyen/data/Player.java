package de.unihannover.nguyen.data;

import java.util.LinkedList;

/**
 * Class which represents the player snake
 */
public class Player {

    Direction playerDirection; // default value

    LinkedList<Coordinate> snakeSegmentList;


    /**
     * Constructor of the snake. Direction and the entire list of all used coordiantes are used.
     *
     * @param playerDirection the direction the player will be facing
     * @param segList List containing all coordinates of the snake segments of the player
     */
    public Player(Direction playerDirection, LinkedList<Coordinate> segList) {
        this.playerDirection = playerDirection;
        this.snakeSegmentList = segList;
    }

    /**
     * Steers the player to another direction
     * The direction should not be changed if its identical.
     * It is also not possible to do a quick 180 turn on the spot
     *
     * @param nextDirection The next direction, as an enum
     */
    public void steer(Direction nextDirection) {
        int xHead = getHead().getX();
        int yHead = getHead().getY();

        int xBefore = getBeforeHead().getX();
        int yBefore = getBeforeHead().getY();

//        System.out.println(xHead+";"+yHead);
//        System.out.println(xBefore+";"+yBefore);

        // the player should not be able to do a 180 turn on the spot
        if(xHead == xBefore && yHead-1 == yBefore &&
                nextDirection == Direction.UP) {

        }
        else if (nextDirection == Direction.UP) {
            this.playerDirection = Direction.UP;
        }

        if(xHead == xBefore && yHead+1 == yBefore &&
                nextDirection == Direction.DOWN) {

        }
        else if (nextDirection == Direction.DOWN){
            this.playerDirection = Direction.DOWN;
        }


        if(xHead-1 == xBefore && yHead == yBefore &&
                nextDirection == Direction.LEFT) {
        }
        else if(nextDirection == Direction.LEFT){
            this.playerDirection = Direction.LEFT;
        }

        if(xHead+1 == xBefore && yHead == yBefore &&
                nextDirection == Direction.RIGHT) {
        }
        else if(nextDirection == Direction.RIGHT){
            this.playerDirection = Direction.RIGHT;
        }
    }


    public Coordinate getCurrentPosition() {
        return snakeSegmentList.getLast();
    }


    public Coordinate getTail() {
        return snakeSegmentList.getFirst();
    }

    public Coordinate getHead() {
        return snakeSegmentList.getLast();
    }

    public Coordinate getBeforeHead() {
        return snakeSegmentList.get(snakeSegmentList.size()-2);
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public LinkedList<Coordinate> getSnakeSegmentList() {
        return snakeSegmentList;
    }

}
