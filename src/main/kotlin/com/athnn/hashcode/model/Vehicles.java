package com.athnn.hashcode.model;

import java.util.List;
import java.util.Optional;
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
        for (Vehicle vehicle : vehicles) {
            Optional<Ride> bestRideOpt = configuration.getRides().getBestRide(vehicle, step);
            bestRideOpt.ifPresent(bestRide -> {
                vehicle.assignRide(bestRide);
                configuration.getRides().removeRide(bestRide);
            });
        }
    }
}
