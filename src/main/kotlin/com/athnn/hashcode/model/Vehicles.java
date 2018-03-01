package com.athnn.hashcode.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vehicles {

    private List<Vehicle> vehicles;

    public Vehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicles(int n) {
        this.vehicles = IntStream.range(0, n)
                .mapToObj(i -> new Vehicle(new Point(0L, 0L)))
                .collect(Collectors.toList());
    }

    public Vehicles getWaitingVehicles() {
        return new Vehicles(vehicles.stream()
                .filter(Vehicle::isWaiting)
                .collect(Collectors.toList()));
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void notifyStep(Long step) {
        vehicles.forEach(vehicle -> vehicle.notifyStep(step));
    }

    public void assignRides(Configuration configuration, Long step) {

        List<Ride> discardedRides = new ArrayList<>();
        for (Vehicle vehicle : getWaitingVehicles().getVehicles()) { //TODO refactor
            Optional<AbstractMap.SimpleEntry<Vehicle, Ride>> bestRideAndVehicleOpt = getBestRideAndVehicle(configuration.getRides(), discardedRides, step);
            bestRideAndVehicleOpt.ifPresent(bestRideAndVehicle -> {
                discardedRides.add(bestRideAndVehicle.getValue());
                vehicle.assignRide(bestRideAndVehicle.getValue());
            });
        }
    }

    private Optional<AbstractMap.SimpleEntry<Vehicle, Ride>> getBestRideAndVehicle(Rides rides, List<Ride> discardedRides, Long step) {
        List<AbstractMap.SimpleEntry<Vehicle, List<AbstractMap.SimpleEntry<Long, Ride>>>> sortedRidesByVehicle = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            sortedRidesByVehicle.add(new AbstractMap.SimpleEntry<>(vehicle, rides.exclude(discardedRides).getSortedRidesWithPoints(vehicle, step)));
        }
        return sortedRidesByVehicle.stream()
                .sorted(Comparator.comparingLong(a -> a.getValue().stream().findFirst().map(AbstractMap.SimpleEntry::getKey).orElse(Long.MAX_VALUE)))
                .map(e -> e.getValue().stream().findFirst().map(ride -> new AbstractMap.SimpleEntry<>(e.getKey(), ride.getValue())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }
}
