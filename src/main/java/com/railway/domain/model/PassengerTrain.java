package com.railway.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PassengerTrain {

    private final String trainNumber;
    private final Locomotive locomotive;
    private final List<PassengerCarriage> carriages;

    public PassengerTrain(String trainNumber, Locomotive locomotive, List<PassengerCarriage> carriages) {
        if (trainNumber == null || trainNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Немає номера поїзда");
        }
        if (locomotive == null) {
            throw new IllegalArgumentException("Немає локомотива");
        }
        if (carriages == null || carriages.isEmpty()) {
            throw new IllegalArgumentException("Немає вагонів");
        }
        this.trainNumber = trainNumber.trim();
        this.locomotive = locomotive;
        this.carriages = List.copyOf(new ArrayList<>(carriages));
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public List<PassengerCarriage> getCarriages() {
        return Collections.unmodifiableList(carriages);
    }

    public List<RollingStock> getAllRollingStock() {
        List<RollingStock> units = new ArrayList<>();
        units.add(locomotive);
        units.addAll(carriages);
        return Collections.unmodifiableList(units);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        PassengerTrain that = (PassengerTrain) other;
        return trainNumber.equals(that.trainNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber);
    }
}
