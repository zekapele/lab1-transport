package com.railway.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocomotiveTest {

    @Test
    void testDescribeRole() {
        Locomotive locomotive = new Locomotive("LOK-1", 120.0, 300);

        String role = locomotive.describeRole();

        assertTrue(role.contains("LOK-1"));
    }

    @Test
    void testBadTraction() {
        assertThrows(IllegalArgumentException.class,
                () -> new Locomotive("LOK-1", 120.0, 0));
    }
}
