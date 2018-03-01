package com.athnn.hashcode.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class Vehicle {

    private Point position;
    private Ride assignedRide;
    private boolean isRideStarted;
    private List<Ride> completedRides = new ArrayList<>();

    public Vehicle(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void assignRide(Ride ride) {
        this.assignedRide = ride;
    }

    public boolean isWaiting() {
        return Objects.isNull(assignedRide);
    }

    public void notifyStep(Long step) {

        if (Objects.nonNull(assignedRide)) {
            evaluateRide(step);
        }
    }

    private void evaluateRide(Long step) {
        if (isRideStarted) {
            moveTo(assignedRide.getEnd());
            checkEndPosition();
        } else {
            evaluateReadyToStart(step);
        }
    }

    private void evaluateReadyToStart(Long step) {
        if (position.equals(assignedRide.getStart())) {
            evaluateStepForStart(step);
        } else {
            moveTo(assignedRide.getStart());
        }
    }

    private void evaluateStepForStart(Long step) {
        if (step >= assignedRide.getStartRound()) {
            isRideStarted = true;
            moveTo(assignedRide.getEnd());
        }
    }

    private void moveTo(Point destination) {
        //TODO log
        Point distance = Point.getDistance(position, destination);
        if (abs(distance.getX()) >= abs(distance.getY())) {
            position = new Point(position.getX() + sign(distance.getX()), position.getY());
        } else {
            position = new Point(position.getX(), position.getY() + sign(distance.getY()));
        }
    }

    private Long sign(Long x) {
        return x >= 0L ? 1L : -1L;
    }

    private void checkEndPosition() {
        if (position.equals(assignedRide.getEnd())) {
            isRideStarted = false;
            completedRides.add(assignedRide);
            assignedRide = null;
        }
    }

    public Rides getCompletedRides() {
        return new Rides(completedRides);
    }
}
