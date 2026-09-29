package com.railway.domain.model;

import java.util.Objects;

public abstract class RollingStock {

    private final String inventoryNumber;
    private final double tareWeightTonnes;

    protected RollingStock(String inventoryNumber, double tareWeightTonnes) {
        if (inventoryNumber == null || inventoryNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Немає інвентарного номера");
        }
        if (tareWeightTonnes <= 0) {
            throw new IllegalArgumentException("Невірна вага");
        }
        this.inventoryNumber = inventoryNumber.trim();
        this.tareWeightTonnes = tareWeightTonnes;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public double getTareWeightTonnes() {
        return tareWeightTonnes;
    }

    public abstract String describeRole();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        RollingStock that = (RollingStock) other;
        return inventoryNumber.equals(that.inventoryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryNumber);
    }
}
