package com.railway.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {

    @Test
    void testMainDefaultConfig() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }
}
