package com.athnn.hashcode.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vehicles {

    private List<Vehicle> vehicles;

    public Vehicles(int n) {
        this.vehicles = IntStream.range(0, n)
                .mapToObj(i -> new Vehicle(new Point(0L, 0L)))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getWaitingVehicles() {
        return vehicles.stream()
                .filter(Vehicle::isWaiting)
                .collect(Collectors.toList());
    }

    public void notifyStep(int step) {
        vehicles.stream().forEach(vehicle -> vehicle.notifyStep(step));
    }
}
