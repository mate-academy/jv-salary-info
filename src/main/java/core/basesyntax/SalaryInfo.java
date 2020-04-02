package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет core.basesyntax.exception и в нём класс-ошибку IllegalDateParametersException.
     * Сделать так,
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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        StringBuilder sb = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        if (localDateFrom.isAfter(localDateTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        data = filterByDate(data, dateFrom, dateTo);
        for (String name : names) {
            int count = 0;
            for (String line : data) {
                String[] arr = line.split("[ ]");
                if (arr[1].equals(name)) {
                    count = count + Integer.parseInt(arr[2]) * Integer.parseInt(arr[3]);
                }
            }
            sb.append("\n").append(name).append(" - ").append(count);
        }
        return sb.toString();
    }

    private static String[] filterByDate(String[] data, String dateFrom, String dateTo) {
        List<String> list = new ArrayList<>();
        for (String s : data) {
            LocalDate rowLocalDate = LocalDate.parse(s.substring(0, 10), FORMATTER);
            LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
            if ((rowLocalDate.isAfter(localDateFrom) || rowLocalDate.equals(localDateFrom))
                    && (rowLocalDate.isBefore(localDateTo) || rowLocalDate.equals(localDateTo))) {
                list.add(s);
            }
        }
        String[] arr = new String[list.size()];
        return list.toArray(arr);
    }
}
