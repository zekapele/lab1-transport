package com.railway.domain.model;

import com.railway.domain.enums.ComfortLevel;

public class OpenSaloonCarriage extends PassengerCarriage {

    private final boolean hasBistro;

    public OpenSaloonCarriage(
            String inventoryNumber,
            double tareWeightTonnes,
            int seatCapacity,
            ComfortLevel comfortLevel,
            int passengerCount,
            int luggagePieces,
            boolean hasBistro) {
        super(inventoryNumber, tareWeightTonnes, seatCapacity, comfortLevel,
                passengerCount, luggagePieces);
        this.hasBistro = hasBistro;
    }

    public boolean hasBistro() {
        return hasBistro;
    }

    @Override
    public String describeRole() {
        String type = hasBistro ? "плацкарт з буфетом " : "плацкарт ";
        return type + getInventoryNumber() + ", комфорт " + getComfortLevel();
    }
}
