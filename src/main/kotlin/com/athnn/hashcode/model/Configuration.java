package com.athnn.hashcode.model;

public class Configuration {

    private City city;
    private Long bonus;
    private Rides rides;
    private Long maxSteps;
    private Vehicles vehicles;

        public Configuration(City city, Long bonus, Rides rides, Long maxSteps, Vehicles vehicles) {
        this.city = city;
        this.bonus = bonus;
        this.rides = rides;
        this.maxSteps = maxSteps;
        this.vehicles = vehicles;
    }

    public City getCity() {
        return city;
    }

    public Long getBonus() {
        return bonus;
    }

    public Rides getRides() {
        return rides;
    }

    public Long getMaxSteps() {
        return maxSteps;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }
}
