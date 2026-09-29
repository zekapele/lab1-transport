package com.railway.service;

import com.railway.domain.enums.ComfortLevel;
import com.railway.domain.model.PassengerCarriage;
import com.railway.domain.model.PassengerTrain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrainService {

    public int countTotalPassengers(PassengerTrain train) {
        int sum = 0;
        for (PassengerCarriage carriage : train.getCarriages()) {
            sum += carriage.getPassengerCount();
        }
        return sum;
    }

    public int countTotalLuggage(PassengerTrain train) {
        int sum = 0;
        for (PassengerCarriage carriage : train.getCarriages()) {
            sum += carriage.getLuggagePieces();
        }
        return sum;
    }

    public List<PassengerCarriage> sortByComfort(PassengerTrain train) {
        List<PassengerCarriage> list = new ArrayList<>(train.getCarriages());
        list.sort(new Comparator<PassengerCarriage>() {
            @Override
            public int compare(PassengerCarriage a, PassengerCarriage b) {
                int byComfort = Integer.compare(
                        a.getComfortLevel().getRank(),
                        b.getComfortLevel().getRank());
                if (byComfort != 0) {
                    return byComfort;
                }
                return a.getInventoryNumber().compareTo(b.getInventoryNumber());
            }
        });
        return list;
    }

    public List<PassengerCarriage> findByPassengerCount(
            PassengerTrain train, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min > max");
        }
        List<PassengerCarriage> result = new ArrayList<>();
        for (PassengerCarriage carriage : train.getCarriages()) {
            int p = carriage.getPassengerCount();
            if (p >= min && p <= max) {
                result.add(carriage);
            }
        }
        return result;
    }
}
