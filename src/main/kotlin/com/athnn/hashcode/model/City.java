package com.athnn.hashcode.model;

public class City {
    // cols
    private Long width;
    // rows
    private Long height;

    public City(Long width, Long height) {
        this.width = width;
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public Long getHeight() {
        return height;
    }
}
