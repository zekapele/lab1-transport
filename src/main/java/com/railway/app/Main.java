package com.railway.app;

import com.railway.domain.model.PassengerCarriage;
import com.railway.domain.model.PassengerTrain;
import com.railway.domain.model.RollingStock;
import com.railway.io.TrainConfigurationLoader;
import com.railway.service.TrainService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TrainConfigurationLoader loader = new TrainConfigurationLoader();
        TrainService service = new TrainService();

        try {
            PassengerTrain train;
            // якщо передали шлях — читаємо з диска
            if (args.length > 0) {
                train = loader.loadFromFile(Path.of(args[0]));
            } else {
                train = loader.loadFromClasspath("/config/kyiv-express.train");
            }

            int passengers = service.countTotalPassengers(train);
            int luggage = service.countTotalLuggage(train);
            List<PassengerCarriage> byComfort = service.sortByComfort(train);
            List<PassengerCarriage> byRange = service.findByPassengerCount(train, 30, 55);

            printReport(train, passengers, luggage, byComfort, byRange, 30, 55);
        } catch (IOException e) {
            System.err.println("Не вдалось прочитати файл: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка даних: " + e.getMessage());
        }
    }

    private static void printReport(
            PassengerTrain train,
            int passengers,
            int luggage,
            List<PassengerCarriage> byComfort,
            List<PassengerCarriage> byRange,
            int min,
            int max) {
        System.out.println("Поїзд № " + train.getTrainNumber());
        System.out.println("Склад:");
        for (RollingStock unit : train.getAllRollingStock()) {
            System.out.println("  " + unit.describeRole());
        }
        System.out.println("Усього пасажирів: " + passengers);
        System.out.println("Усього одиниць багажу: " + luggage);
        System.out.print("Вагони (комфорт, зростання): ");
        printNumbers(byComfort);
        System.out.println();
        System.out.print("Вагони з " + min + ".." + max + " пасажирами: ");
        printNumbers(byRange);
        System.out.println();
    }

    private static void printNumbers(List<PassengerCarriage> carriages) {
        for (int i = 0; i < carriages.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(carriages.get(i).getInventoryNumber());
        }
    }
}
