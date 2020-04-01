package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            throws Exception {

        if (getDate(dateFrom).isAfter(getDate(dateTo))) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder result = new StringBuilder("Отчёт за период ")
                .append(sdf.format(getDate(dateFrom)))
                .append(" - ")
                .append(sdf.format(getDate(dateTo)));

        int salary = 0;
        for (String name : names) {
            for (String currentData : data) {
                if ((name.equals(getName(currentData))) & (dateCondition(currentData, dateFrom, dateTo))) {
                        salary += getHours(currentData) * getPrice(currentData);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
            salary = 0;
        }
        return result.toString();
    }

    private boolean dateCondition(String current, String dateFrom, String dateTo) {
        return (getDate(current).isAfter(getDate(dateFrom))
                | getDate(current).isEqual(getDate(dateFrom)))
                & (getDate(current).isBefore(getDate(dateTo))
                | getDate(current).isEqual(getDate(dateTo)));
    }

    private static LocalDate getDate(String s) {
        int day = Integer.valueOf(s.substring(0, 2));
        int month = Integer.valueOf(s.substring(3, 5));
        int year = Integer.valueOf(s.substring(6, 10));

        return LocalDate.of(year, month, day);
    }

    private static String getName(String s) {
        return s.replaceAll("[\\s\\.\\d]", "");
    }

    private static int getHours(String s) {
        return Integer.valueOf(s.split(" ")[3]);
    }

    private static int getPrice(String s) {
        return Integer.valueOf(s.split(" ")[2]);
    }
}
