package com.athnn.hashcode.model;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rides {

    private List<Ride> rides;

    public Rides(List<Ride> rides) {
        this.rides = rides;
    }
    public Rides(Stream<Ride> rides) {this.rides = rides.collect(Collectors.toList());}

    public List<Ride> getRides() {
        return rides;
    }

    public void removeRide(Ride ride) {
        rides.removeIf(r -> r.equals(ride));
    }

    public Optional<Ride> getBestRide(Vehicle vehicle, Long step) {
        return rides.stream()
                .map(ride -> new AbstractMap.SimpleEntry<>(getScore(ride, vehicle.getPosition(), step), ride))
                .sorted(Comparator.comparingLong(AbstractMap.SimpleEntry::getKey))
                .map(AbstractMap.SimpleEntry::getValue)
                .findFirst();
    }

    private Long getScore(Ride ride, Point position, Long step) {
        Point distance = Point.getDistance(position, ride.getStart());
        return Math.max(ride.getStartRound() - step, Math.abs(distance.getX()) + Math.abs(distance.getY()));
    }

    @Override
    public String toString() {
        return "";
    }
}
