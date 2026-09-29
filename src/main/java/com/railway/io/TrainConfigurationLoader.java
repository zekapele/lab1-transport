package com.railway.io;

import com.railway.domain.enums.ComfortLevel;
import com.railway.domain.model.CompartmentCarriage;
import com.railway.domain.model.Locomotive;
import com.railway.domain.model.OpenSaloonCarriage;
import com.railway.domain.model.PassengerCarriage;
import com.railway.domain.model.PassengerTrain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TrainConfigurationLoader {

    public PassengerTrain loadFromClasspath(String resourcePath) throws IOException {
        InputStream stream = TrainConfigurationLoader.class.getResourceAsStream(resourcePath);
        if (stream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
        try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader buffered = new BufferedReader(reader)) {
            return parse(buffered.lines().toList());
        }
    }

    public PassengerTrain loadFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        return parse(lines);
    }

    PassengerTrain parse(List<String> rawLines) {
        String trainNumber = null;
        Locomotive locomotive = null;
        List<PassengerCarriage> carriages = new ArrayList<>();

        for (String rawLine : rawLines) {
            String line = stripComment(rawLine).trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            switch (tokens[0]) {
                case "TRAIN" -> trainNumber = requireToken(tokens, 1, line);
                case "LOCOMOTIVE" -> locomotive = parseLocomotive(tokens, line);
                case "CARRIAGE" -> carriages.add(parseCarriage(tokens, line));
                default -> throw new IllegalArgumentException("Невідомий рядок: " + tokens[0]);
            }
        }

        if (trainNumber == null) {
            throw new IllegalArgumentException("Потрібен рядок TRAIN");
        }
        if (locomotive == null) {
            throw new IllegalArgumentException("Потрібен рядок LOCOMOTIVE");
        }
        return new PassengerTrain(trainNumber, locomotive, carriages);
    }

    private static Locomotive parseLocomotive(String[] tokens, String line) {
        return new Locomotive(
                requireToken(tokens, 1, line),
                parseDouble(tokens, 2, line),
                parseInt(tokens, 3, line));
    }

    private static PassengerCarriage parseCarriage(String[] tokens, String line) {
        String kind = requireToken(tokens, 1, line);
        String inventory = requireToken(tokens, 2, line);
        double tare = parseDouble(tokens, 3, line);
        int seats = parseInt(tokens, 4, line);
        ComfortLevel comfort = ComfortLevel.valueOf(requireToken(tokens, 5, line).toUpperCase());
        int passengers = parseInt(tokens, 6, line);
        int luggage = parseInt(tokens, 7, line);

        return switch (kind.toUpperCase()) {
            case "COMPARTMENT" -> new CompartmentCarriage(
                    inventory, tare, seats, comfort, passengers, luggage, parseInt(tokens, 8, line));
            case "SALOON" -> new OpenSaloonCarriage(
                    inventory, tare, seats, comfort, passengers, luggage,
                    Boolean.parseBoolean(requireToken(tokens, 8, line)));
            default -> throw new IllegalArgumentException("Невідомий тип вагона: " + kind);
        };
    }

    private static String stripComment(String line) {
        int hash = line.indexOf('#');
        return hash >= 0 ? line.substring(0, hash) : line;
    }

    private static String requireToken(String[] tokens, int index, String line) {
        if (index >= tokens.length) {
            throw new IllegalArgumentException("Неповний рядок: " + line);
        }
        return tokens[index];
    }

    private static int parseInt(String[] tokens, int index, String line) {
        try {
            return Integer.parseInt(requireToken(tokens, index, line));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Невірне число: " + line, ex);
        }
    }

    private static double parseDouble(String[] tokens, int index, String line) {
        try {
            return Double.parseDouble(requireToken(tokens, index, line));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Невірне число: " + line, ex);
        }
    }
}
