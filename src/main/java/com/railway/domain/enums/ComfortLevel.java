package com.railway.domain.enums;

public enum ComfortLevel {

    ECONOMY(1),
    STANDARD(2),
    BUSINESS(3),
    LUXURY(4);

    private final int rank;

    ComfortLevel(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
