package com.mfalcier.hashcode.model;

import java.util.List;

public class Configuration {

    private City city;
    private Long bonus;
    private List<Ride> rides;
    private Long maxSteps;

    public Configuration(City city, Long bonus, List<Ride> rides, Long maxSteps) {
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

    public List<Ride> getRides() {
        return rides;
    }

    public Long getMaxSteps() {
        return maxSteps;
    }
}
