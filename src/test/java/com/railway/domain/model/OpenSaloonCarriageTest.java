package com.railway.domain.model;

import com.railway.domain.enums.ComfortLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenSaloonCarriageTest {

    @Test
    void testDescribeRole() {
        OpenSaloonCarriage withBistro = new OpenSaloonCarriage(
                "KVZ-2", 48.0, 72, ComfortLevel.STANDARD, 50, 10, true);
        OpenSaloonCarriage withoutBistro = new OpenSaloonCarriage(
                "KVZ-3", 48.0, 40, ComfortLevel.LUXURY, 20, 5, false);

        assertTrue(withBistro.describeRole().contains("буфетом"));
        assertTrue(withoutBistro.describeRole().contains("KVZ-3"));
    }
}
