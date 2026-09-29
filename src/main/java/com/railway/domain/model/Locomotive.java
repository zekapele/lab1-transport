package com.railway.domain.model;

public class Locomotive extends RollingStock {

    private final int tractionForceKn;

    public Locomotive(String inventoryNumber, double tareWeightTonnes, int tractionForceKn) {
        super(inventoryNumber, tareWeightTonnes);
        if (tractionForceKn <= 0) {
            throw new IllegalArgumentException("Невірна тяга");
        }
        this.tractionForceKn = tractionForceKn;
    }

    public int getTractionForceKn() {
        return tractionForceKn;
    }

    @Override
    public String describeRole() {
        return "локомотив " + getInventoryNumber();
    }
}
