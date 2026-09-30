# lab1 — залізничний транспорт

Лабораторна робота 1, варіант **8 (Транспорт)**.

Консольна програма на Java. Зчитує пасажирський поїзд з текстового файлу і виводить короткий звіт: склад, суми, сортування вагонів, пошук за кількістю пасажирів.

## що робить програма

1. Збирає **пасажирський поїзд** (локомотив + вагони).
2. Рахує **усього пасажирів** і **усього одиниць багажу** по всіх вагонах.
3. **Сортує вагони** за рівнем комфорту (від меншого до більшого).
4. Знаходить вагони, де кількість пасажирів потрапляє в заданий **діапазон** (у `Main` зараз 30…55).

Після запуску в консолі буде щось на кшталт: номер поїзда, список одиниць складу, 153 пасажири, 226 багажу, список інвентарних номерів вагонів.

## класи (коротко)

Ієрархія рухомого складу:

- `RollingStock` — базовий клас (інв. номер, вага).
- `Locomotive` — локомотив.
- `PassengerCarriage` — пасажирський вагон (абстрактний).
- `CompartmentCarriage` — купейний вагон.
- `OpenSaloonCarriage` — плацкарт (є поле чи є буфет).

Інше:

- `PassengerTrain` — поїзд (локомотив + список вагонів).
- `ComfortLevel` — enum комфорту (ECONOMY … LUXURY).
- `TrainService` — підрахунки, сорт, пошук.
- `TrainConfigurationLoader` — читає файл конфігурації.
- `Main` — точка входу, вивід у консоль.

Пакети: `com.railway.domain`, `service`, `io`, `app`.

## файл конфігурації

За замовчуванням: `src/main/resources/config/kyiv-express.train`

Формат (рядки, `#` — коментар):

```
TRAIN номер_поїзда
LOCOMOTIVE інв_номер вага_т тяга_кN
CARRIAGE COMPARTMENT інв вага місця COMFORT пасажири багаж купе
CARRIAGE SALOON інв вага місця COMFORT пасажири багаж true|false
```

COMFORT: `ECONOMY`, `STANDARD`, `BUSINESS`, `LUXURY`.

## як запустити

Тести:

```
mvn clean test
```

Збірка і запуск:

```
mvn package -DskipTests
java -cp target/classes com.railway.app.Main
```

Свій файл (шлях до `.train`):

```
java -cp target/classes com.railway.app.Main src/main/resources/config/kyiv-express.train
```

Потрібні **Java 17** і **Maven**.

## тести

JUnit 5, Mockito (мок поїзда в `TrainServiceTest`). Запускаються через `mvn test`.
