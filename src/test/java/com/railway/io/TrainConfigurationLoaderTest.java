package com.railway.io;

import com.railway.domain.enums.ComfortLevel;
import com.railway.domain.model.CompartmentCarriage;
import com.railway.domain.model.OpenSaloonCarriage;
import com.railway.domain.model.PassengerTrain;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrainConfigurationLoaderTest {

    private final TrainConfigurationLoader loader = new TrainConfigurationLoader();

    @Test
    void loadsFromClasspathResource() throws IOException {
        PassengerTrain train = loader.loadFromClasspath("/config/kyiv-express.train");

        assertEquals("102K", train.getTrainNumber());
        assertEquals(4, train.getCarriages().size());
    }

    @Test
    void parsesInlineConfiguration() {
        List<String> lines = List.of(
                "TRAIN 501K",
                "LOCOMOTIVE LOK-9 100.0 250",
                "CARRIAGE COMPARTMENT KVZ-9 50.0 40 STANDARD 30 12 8",
                "CARRIAGE SALOON KVZ-10 48.0 60 BUSINESS 40 15 true");

        PassengerTrain train = loader.parse(lines);

        assertEquals("501K", train.getTrainNumber());
        assertInstanceOf(CompartmentCarriage.class, train.getCarriages().get(0));
        assertInstanceOf(OpenSaloonCarriage.class, train.getCarriages().get(1));
        assertEquals(ComfortLevel.STANDARD, train.getCarriages().get(0).getComfortLevel());
    }

    @Test
    void ignoresCommentsAndBlankLines() {
        List<String> lines = List.of(
                "# comment",
                "",
                "TRAIN 1K",
                "LOCOMOTIVE L1 90.0 200",
                "CARRIAGE COMPARTMENT C1 50.0 30 ECONOMY 20 5 6");

        PassengerTrain train = loader.parse(lines);

        assertEquals("1K", train.getTrainNumber());
    }

    @Test
    void loadFromFileUsesFilesystem() throws IOException {
        Path sample = Path.of("src/main/resources/config/kyiv-express.train");

        PassengerTrain train = loader.loadFromFile(sample);

        assertEquals("102K", train.getTrainNumber());
    }

    @Test
    void missingTrainDirectiveFails() {
        List<String> lines = List.of("LOCOMOTIVE L1 90.0 200",
                "CARRIAGE COMPARTMENT C1 50.0 30 ECONOMY 20 5 6");

        assertThrows(IllegalArgumentException.class, () -> loader.parse(lines));
    }
}
