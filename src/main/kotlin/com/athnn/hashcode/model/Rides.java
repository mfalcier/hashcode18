package com.athnn.hashcode.model;

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

    public Rides getSortedByBestTime() {
        return new Rides(rides.stream()
                .sorted(Comparator.comparingLong(Ride::getBestArrivalStep)));
    }

    public Optional<Ride> getBestByCurrentStep(Long step) {
        return rides.stream()
                .filter(ride -> ride.getBestArrivalStep()<= step)
                .findFirst();
    }
}
