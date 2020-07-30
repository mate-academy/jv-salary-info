package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    /**
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
     * 16.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 24.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 25.04.2019 Андрей 3 200
     * 22.04.2019 Сергей 7 100
     * 22.04.2019 София 9 100
     * 23.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 5500
     * Андрей - 1200
     * София - 1800</p>
     */

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);

        if (dateStart.isAfter(dateFinish)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder order = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int sumSalary = 0;
            for (String workers : data) {
                String[] array = workers.split(" ");
                LocalDate checkFromDate = LocalDate.parse(array[0], FORMATTER);
                if (array[1].equals(name)
                        && (!checkFromDate.isAfter(dateFinish))
                        && (!checkFromDate.isBefore(dateStart))) {
                    int hoursOfWork = Integer.parseInt(array[2]);
                    int salaryOfHour = Integer.parseInt(array[3]);
                    sumSalary += hoursOfWork * salaryOfHour;
                }
            }
            order.append("\n").append(name).append(" - ").append(sumSalary);
        }
        return order.toString();
    }
}
