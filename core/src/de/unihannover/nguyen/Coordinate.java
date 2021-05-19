package de.unihannover.nguyen;

/**
 * Basic class representing a 2D coordinate with x and y coordinates
 */
class Coordinate {
    int x;
    int y;

    /**
     * Sets the coordinates
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
