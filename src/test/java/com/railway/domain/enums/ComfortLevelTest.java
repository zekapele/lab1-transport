package com.railway.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ComfortLevelTest {

    @Test
    void rankIncreasesWithComfort() {
        assertTrue(ComfortLevel.LUXURY.getRank() > ComfortLevel.ECONOMY.getRank());
    }
}
