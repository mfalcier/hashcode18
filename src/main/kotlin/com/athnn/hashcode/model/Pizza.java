package com.athnn.hashcode.model;

import java.util.List;
import java.util.Optional;

public class Pizza {

    public Pizza(List<List<String>> matrix) {
       this.matrix = matrix;
    }

    private List<List<String>> matrix;

    public int getWidth() {
        return Optional.ofNullable(matrix.get(0))
                .map(List::size)
                .orElse(0);
    }

    public int getHeight() {
        return matrix.size();
    }
}
