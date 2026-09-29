package com.railway.domain.model;

import com.railway.domain.enums.ComfortLevel;

public abstract class PassengerCarriage extends RollingStock {

    private int passengerCount;
    private int luggagePieces;
    private final ComfortLevel comfortLevel;
    private final int seatCapacity;

    protected PassengerCarriage(
            String inventoryNumber,
            double tareWeightTonnes,
            int seatCapacity,
            ComfortLevel comfortLevel,
            int passengerCount,
            int luggagePieces) {
        super(inventoryNumber, tareWeightTonnes);
        if (seatCapacity <= 0) {
            throw new IllegalArgumentException("Невірна місткість");
        }
        if (comfortLevel == null) {
            throw new IllegalArgumentException("Не задано рівень комфорту");
        }
        this.seatCapacity = seatCapacity;
        this.comfortLevel = comfortLevel;
        setPassengerCount(passengerCount);
        setLuggagePieces(luggagePieces);
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        if (passengerCount < 0 || passengerCount > seatCapacity) {
            throw new IllegalArgumentException("Невірна к-сть пасажирів");
        }
        this.passengerCount = passengerCount;
    }

    public int getLuggagePieces() {
        return luggagePieces;
    }

    public void setLuggagePieces(int luggagePieces) {
        if (luggagePieces < 0) {
            throw new IllegalArgumentException("Невірна к-сть багажу");
        }
        this.luggagePieces = luggagePieces;
    }

    public boolean isWithinPassengerRange(int minInclusive, int maxInclusive) {
        return passengerCount >= minInclusive && passengerCount <= maxInclusive;
    }

    @Override
    public abstract String describeRole();
}
