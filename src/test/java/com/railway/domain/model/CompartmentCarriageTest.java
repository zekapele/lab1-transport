package com.railway.domain.model;

import com.railway.domain.enums.ComfortLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompartmentCarriageTest {

    @Test
    void updatesPassengerCountWithinCapacity() {
        CompartmentCarriage carriage = new CompartmentCarriage(
                "KVZ-1", 50.0, 54, ComfortLevel.STANDARD, 10, 5, 9);

        carriage.setPassengerCount(40);

        assertEquals(40, carriage.getPassengerCount());
    }

    @Test
    void passengerRangeCheck() {
        CompartmentCarriage carriage = new CompartmentCarriage(
                "KVZ-1", 50.0, 54, ComfortLevel.STANDARD, 45, 5, 9);

        assertTrue(carriage.isWithinPassengerRange(40, 50));
        assertFalse(carriage.isWithinPassengerRange(46, 54));
    }

    @Test
    void rejectsPassengerCountAboveCapacity() {
        CompartmentCarriage carriage = new CompartmentCarriage(
                "KVZ-1", 50.0, 54, ComfortLevel.STANDARD, 10, 5, 9);

        assertThrows(IllegalArgumentException.class, () -> carriage.setPassengerCount(55));
    }
}
