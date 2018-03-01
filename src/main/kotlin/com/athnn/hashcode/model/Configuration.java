package com.athnn.hashcode.model;

public class Configuration {

    private City city;
    private Long bonus;
    private Rides rides;
    private Long maxSteps;

        public Configuration(City city, Long bonus, Rides rides, Long maxSteps) {
        this.city = city;
        this.bonus = bonus;
        this.rides = rides;
        this.maxSteps = maxSteps;
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
}
