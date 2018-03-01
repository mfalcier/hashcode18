package com.mfalcier.hashcode.model;

public class Ride {

    private Point start;
    private Point end;
    private Long startRound;
    private Long endRound;

    public Ride(Point start, Point end, Long startRound, Long endRound) {
        this.start = start;
        this.end = end;
        this.startRound = startRound;
        this.endRound = endRound;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Long getStartRound() {
        return startRound;
    }

    public Long getEndRound() {
        return endRound;
    }
}
