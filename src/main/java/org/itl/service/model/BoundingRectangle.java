package org.itl.service.model;

public class BoundingRectangle {

    private final Point topLeft;
    private final Point bottomRight;

    public BoundingRectangle(final Point topLeft, final Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getMiddleLeft() {
        return new Point(topLeft.getX(), bottomRight.getY() - (bottomRight.getY()-topLeft.getY())/2);
    }

    public Point getBottomLeft() {
        return new Point(topLeft.getX(), bottomRight.getY());
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public int getTopY() {
        return topLeft.getY();
    }

    public int getBottomY() {
        return bottomRight.getY();
    }

    public Point getTopRight() {
        return new Point(bottomRight.getX(), topLeft.getY());
    }

    public int getRightX() {
        return bottomRight.getX();
    }

    public int getLeftX() {
        return topLeft.getX();
    }

    public boolean contains(BoundingRectangle other) {
        boolean result = true;

        result &= this.topLeft.getX() <= other.topLeft.getX();
        result &= this.topLeft.getY() <= other.topLeft.getY();

        result &= this.bottomRight.getX() >= other.bottomRight.getX();
        result &= this.bottomRight.getY() >= other.bottomRight.getY();

        return result;
    }

    public boolean isInFirstCadrant(BoundingRectangle boundingRectangle) {
        return isInFirstCadrant(boundingRectangle, 0);
    }

    public boolean isInFirstCadrant(BoundingRectangle boundingRectangle, int yAxisTolerance) {
        int x = this.topLeft.getX() + (this.bottomRight.getX() - this.topLeft.getX())/2;
        int y = this.topLeft.getY() + (this.bottomRight.getY() - this.topLeft.getY())/2;
        boolean result = true;

        result &= x < boundingRectangle.topLeft.getX();
        result &= y + yAxisTolerance > boundingRectangle.bottomRight.getY();

        return  result;
    }

    public boolean isNearOnRightside(BoundingRectangle boundingRectangle) {
        return true;
    }

    /**
     * THIS is higher than OTHER if B is positioned above A.
     *         B----+
     *         |    |
     *         |THIS|
     * A-----+ |    |
     * |     | +----+
     * |OTHER|
     * |     |
     * +-----+
     * @param other
     * @return
     */
    public boolean isHigher(BoundingRectangle other) {
        return other.topLeft.getY() > this.topLeft.getY();
    }

    @Override
    public String toString() {
        return "tl:" + topLeft + " br:" + bottomRight;
    }
}
