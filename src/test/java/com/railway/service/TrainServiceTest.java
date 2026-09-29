package com.railway.service;

import com.railway.domain.enums.ComfortLevel;
import com.railway.domain.model.CompartmentCarriage;
import com.railway.domain.model.Locomotive;
import com.railway.domain.model.OpenSaloonCarriage;
import com.railway.domain.model.PassengerCarriage;
import com.railway.domain.model.PassengerTrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TrainServiceTest {

    private TrainService service;
    private PassengerTrain train;

    @BeforeEach
    void setUp() {
        service = new TrainService();
        Locomotive locomotive = new Locomotive("LOK-1", 130.0, 300);
        PassengerCarriage economy = new CompartmentCarriage(
                "A-1", 50.0, 54, ComfortLevel.ECONOMY, 48, 10, 9);
        PassengerCarriage luxury = new OpenSaloonCarriage(
                "B-1", 49.0, 40, ComfortLevel.LUXURY, 22, 5, false);
        PassengerCarriage standard = new OpenSaloonCarriage(
                "C-1", 48.0, 72, ComfortLevel.STANDARD, 55, 20, true);
        train = new PassengerTrain("200K", locomotive, List.of(economy, luxury, standard));
    }

    @Test
    void testPassengersSum() {
        assertEquals(125, service.countTotalPassengers(train));
    }

    @Test
    void testLuggageSum() {
        assertEquals(35, service.countTotalLuggage(train));
    }

    @Test
    void testSortByComfort() {
        List<PassengerCarriage> sorted = service.sortByComfort(train);
        assertEquals("A-1", sorted.get(0).getInventoryNumber());
        assertEquals("C-1", sorted.get(1).getInventoryNumber());
        assertEquals("B-1", sorted.get(2).getInventoryNumber());
    }

    @Test
    void testFindByPassengers() {
        List<PassengerCarriage> found = service.findByPassengerCount(train, 30, 55);
        assertEquals(2, found.size());
    }

    @Test
    void testBadRange() {
        assertThrows(IllegalArgumentException.class,
                () -> service.findByPassengerCount(train, 60, 10));
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class WithMock {

        @Mock
        private PassengerTrain train;

        @Mock
        private PassengerCarriage carriage1;

        @Mock
        private PassengerCarriage carriage2;

        @Test
        void testMockPassengers() {
            when(train.getCarriages()).thenReturn(List.of(carriage1, carriage2));
            when(carriage1.getPassengerCount()).thenReturn(10);
            when(carriage2.getPassengerCount()).thenReturn(15);

            assertEquals(25, new TrainService().countTotalPassengers(train));
        }
    }
}
