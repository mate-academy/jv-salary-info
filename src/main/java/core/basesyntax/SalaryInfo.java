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
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {

        LocalDate dateFromParsed = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToParsed = LocalDate.parse(dateTo, formatter);

        if (dateFromParsed.isAfter(dateToParsed)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        String[] personsWH = new String[4];
        int[] zp = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            personsWH = data[i].split(" ");
            LocalDate workingDate = LocalDate.parse(personsWH[0], formatter);
            if ((workingDate.isAfter(dateFromParsed) && workingDate.isBefore(dateToParsed))
                    || workingDate.isEqual(dateFromParsed) || workingDate.isEqual(dateToParsed)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(personsWH[1])) {
                        zp[j] += Integer.parseInt(personsWH[2]) * Integer.parseInt(personsWH[3]);
                    }
                }
            }
        }
        StringBuilder report;
        report = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        for (int i = 0; i < names.length; i++) {
            report.append(names[i] + " - " + zp[i] + "\n");
        }
        return report.toString();
    }
}
