package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        StringBuilder report = new StringBuilder("Отчёт за период " + dateFrom + " - "
                + dateTo + "\n");
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMAT);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMAT);

        if (dateStart.isAfter(dateFinish)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        dateStart = dateStart.minusDays(1);
        dateFinish = dateFinish.plusDays(1);
        int[] cashTotal = new int[names.length];
        for (String line : data) {
            String[] lineSepareted = line.split(" ");
            if (dateFinish.isAfter(LocalDate.parse(lineSepareted[0], FORMAT))
                    && LocalDate.parse(lineSepareted[0], FORMAT).isAfter(dateStart)) {
                cashTotal[Arrays.asList(names).indexOf(lineSepareted[1])] +=
                        Integer.parseInt(lineSepareted[2]) * Integer.parseInt(lineSepareted[3]);
            }
        }
        for (int i = 0; i < names.length; i++) {
            report.append(names[i] + " - " + cashTotal[i] + "\n");
        }
        return report.toString().trim();

    }
}
