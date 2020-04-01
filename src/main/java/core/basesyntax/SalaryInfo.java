package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    /*
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = parseDate(dateFrom);
        LocalDate to = parseDate(dateTo);
        if (from.isAfter(to)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        Map<String, Integer> nameToEarnings = createNameToEarningsMap(names);

        for (String record : data) {
            EarningsRecord earningsRecord = EarningsRecord.create(record.split(" "));
            if (filterEarningsRecord(earningsRecord, from, to, nameToEarnings)) {
                processRecord(earningsRecord, nameToEarnings);
            }
        }
        return generateReport(names, dateFrom, dateTo, nameToEarnings);
    }

    private boolean filterEarningsRecord(
            EarningsRecord earningsRecord,
            LocalDate fromDate,
            LocalDate toDate,
            Map<String, Integer> nameToEarnings
    ) {
        return nameToEarnings.containsKey(earningsRecord.name)
                && !earningsRecord.date.isBefore(fromDate)
                && !earningsRecord.date.isAfter(toDate);
    }

    private void processRecord(EarningsRecord earningsRecord, Map<String, Integer> nameToEarnings) {
        Integer earnings = nameToEarnings.get(earningsRecord.name)
                + earningsRecord.hours * earningsRecord.hourlyRate;
        nameToEarnings.put(earningsRecord.name, earnings);
    }

    private Map<String, Integer> createNameToEarningsMap(String[] names) {
        Map<String, Integer> nameToEarnings = new HashMap<>();
        for (String name : names) {
            nameToEarnings.put(name, 0);
        }
        return nameToEarnings;
    }

    private String generateReport(String[] names, String dateFrom, String dateTo,
            Map<String, Integer> nameToEarnings) {
        StringBuilder sb = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        for (String name : names) {
            sb.append("\n").append(name).append(" - ").append(nameToEarnings.get(name));
        }
        return sb.toString();
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
    }

    private static class EarningsRecord {
        LocalDate date;
        String name;
        int hours;
        int hourlyRate;

        static EarningsRecord create(String[] fields) {
            EarningsRecord earningsRecord = new EarningsRecord();
            earningsRecord.date = LocalDate.parse(fields[0], FORMATTER);
            earningsRecord.name = fields[1];
            earningsRecord.hours = Integer.parseInt(fields[2]);
            earningsRecord.hourlyRate = Integer.parseInt(fields[3]);
            return earningsRecord;
        }

    }

}
