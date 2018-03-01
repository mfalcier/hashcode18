package com.athnn.hashcode.model;

import java.util.List;

public class City {

    private Long width;
    private Long height;
    private List<Vehicle> vehicles;

    public City(Long width, Long height, List<Vehicle> vehicles) {
        this.width = width;
        this.height = height;
        this.vehicles = vehicles;
    }

    public Long getWidth() {
        return width;
    }

    public Long getHeight() {
        return height;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
