package de.unihannover.nguyen;

import java.util.LinkedList;

class Player {
    boolean playerUp = false;
    boolean playerDown = false;
    boolean playerLeft = false;
    boolean playerRight = false;

    Direction playerDirection; // default value

    LinkedList<Coordinate> playerQueue;


    public Player(Direction playerDirection, LinkedList<Coordinate> playerQueue) {
        this.playerDirection = playerDirection;
        this.playerQueue = playerQueue;
    }

    public void steer(Direction nextDirection) {
        int xHead = getHead().getX();
        int yHead = getHead().getY();

        int xBefore = getBeforeHead().getX();
        int yBefore = getBeforeHead().getY();

        System.out.println(xHead+";"+yHead);
        System.out.println(xBefore+";"+yBefore);

        if(xHead == xBefore && yHead-1 == yBefore &&
                nextDirection == Direction.UP) {

        }
        else if (nextDirection == Direction.UP) {
            System.out.println("GOING UP");
            this.playerDirection = Direction.UP;
        }

        if(xHead == xBefore && yHead+1 == yBefore &&
                nextDirection == Direction.DOWN) {

        }
        else if (nextDirection == Direction.DOWN){
            System.out.println("GOING DOWN");
            this.playerDirection = Direction.DOWN;
        }


        if(xHead-1 == xBefore && yHead == yBefore &&
                nextDirection == Direction.LEFT) {
        }
        else if(nextDirection == Direction.LEFT){
            System.out.println("GOING LEFT");
            this.playerDirection = Direction.LEFT;
        }

        if(xHead+1 == xBefore && yHead == yBefore &&
                nextDirection == Direction.RIGHT) {
        }
        else if(nextDirection == Direction.RIGHT){
            System.out.println("GOING RIGHT");
            this.playerDirection = Direction.RIGHT;
        }



    }


    public Coordinate getCurrentPosition() {
        return playerQueue.getLast();
    }


    public Coordinate getTail() {
        return playerQueue.getFirst();
    }

    public Coordinate getHead() {
        return playerQueue.getLast();
    }

    public Coordinate getBeforeHead() {
        return playerQueue.get(playerQueue.size()-2);
    }


    public boolean isPlayerUp() {
        return playerUp;
    }

    public void setPlayerUp(boolean playerUp) {
        this.playerUp = playerUp;
    }

    public boolean isPlayerDown() {
        return playerDown;
    }

    public void setPlayerDown(boolean playerDown) {
        this.playerDown = playerDown;
    }

    public boolean isPlayerLeft() {
        return playerLeft;
    }

    public void setPlayerLeft(boolean playerLeft) {
        this.playerLeft = playerLeft;
    }

    public boolean isPlayerRight() {
        return playerRight;
    }

    public void setPlayerRight(boolean playerRight) {
        this.playerRight = playerRight;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public void setPlayerDirection(Direction playerDirection) {
        this.playerDirection = playerDirection;
    }

    public LinkedList<Coordinate> getPlayerQueue() {
        return playerQueue;
    }

    public void setPlayerQueue(LinkedList<Coordinate> playerQueue) {
        this.playerQueue = playerQueue;
    }

}
