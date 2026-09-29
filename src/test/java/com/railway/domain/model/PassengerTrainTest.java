package com.railway.domain.model;

import com.railway.domain.enums.ComfortLevel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PassengerTrainTest {

    @Test
    void exposesLocomotiveAndCarriages() {
        Locomotive locomotive = new Locomotive("LOK-1", 130.0, 310);
        PassengerCarriage carriage = new CompartmentCarriage(
                "KVZ-1", 52.0, 54, ComfortLevel.ECONOMY, 40, 30, 9);
        PassengerTrain train = new PassengerTrain("101K", locomotive, List.of(carriage));

        assertEquals("101K", train.getTrainNumber());
        assertEquals(2, train.getAllRollingStock().size());
        assertEquals(1, train.getCarriages().size());
    }

    @Test
    void requiresAtLeastOneCarriage() {
        Locomotive locomotive = new Locomotive("LOK-1", 130.0, 310);

        assertThrows(IllegalArgumentException.class,
                () -> new PassengerTrain("101K", locomotive, List.of()));
    }
}
