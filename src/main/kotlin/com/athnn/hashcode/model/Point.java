package com.athnn.hashcode.model;

import java.util.Objects;

public class Point {

    private Long x;
    private Long y;

    public Point(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Point)) {
            return false;
        }

        Point cmp = (Point) obj;
        return Objects.equals(cmp.x, x) && Objects.equals(cmp.y, y);
    }

    public static Point getDistance(Point position, Point destination) {
        return new Point(position.x - destination.x, position.y - destination.y);
    }
}
