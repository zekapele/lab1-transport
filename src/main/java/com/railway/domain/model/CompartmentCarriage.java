package com.railway.domain.model;

import com.railway.domain.enums.ComfortLevel;

public class CompartmentCarriage extends PassengerCarriage {

    private final int compartmentCount;

    public CompartmentCarriage(
            String inventoryNumber,
            double tareWeightTonnes,
            int seatCapacity,
            ComfortLevel comfortLevel,
            int passengerCount,
            int luggagePieces,
            int compartmentCount) {
        super(inventoryNumber, tareWeightTonnes, seatCapacity, comfortLevel,
                passengerCount, luggagePieces);
        if (compartmentCount <= 0) {
            throw new IllegalArgumentException("Невірна к-сть купе");
        }
        this.compartmentCount = compartmentCount;
    }

    public int getCompartmentCount() {
        return compartmentCount;
    }

    @Override
    public String describeRole() {
        return "купейний вагон " + getInventoryNumber()
                + ", комфорт " + getComfortLevel();
    }
}
