package com.railway.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RollingStockTest {

    @Test
    void equalityBasedOnInventoryNumber() {
        Locomotive first = new Locomotive("LOK-1", 130.0, 310);
        Locomotive sameId = new Locomotive("LOK-1", 140.0, 320);
        Locomotive other = new Locomotive("LOK-2", 130.0, 310);

        assertEquals(first, sameId);
        assertNotEquals(first, other);
    }
}
