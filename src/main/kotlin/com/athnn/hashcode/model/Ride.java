package com.athnn.hashcode.model;

public class Ride {

    private Long id;
    private Point start;
    private Point end;
    private Long startRound;
    private Long endRound;

    public Ride(Long id, Point start, Point end, Long startRound, Long endRound) {
        this.id = id;
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

    public Long getId() {
        return this.id;
    }

    public Long getEffectiveTime() {
        return 0L; //TODO calculate distance between the two points
    }
    //TODO totalTime
    public Long getBestArrivalStep() {
        return 0L; //TODO startRound + effectiveTime
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Ride)) {
            return false;
        }
        Ride cmp = (Ride) obj;
        return this.start.equals(cmp.start) &&
                this.end.equals(cmp.end) &&
                startRound.equals(cmp.startRound) &&
                endRound.equals(cmp.endRound);
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
