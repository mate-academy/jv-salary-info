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
            throws IllegalDateParametersException {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        if (localDateFrom.compareTo(localDateTo) >= 0) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder report = new StringBuilder();
        report = report.append("Отчёт за период " + dateFrom + " - " + dateTo);
        LocalDate date;
        String[] list = new String[4];
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                list = data[j].split(" ");
                date = LocalDate.parse(list[0], formatter);
                if (names[i].equals(list[1])
                        && (localDateFrom.compareTo(date) * date.compareTo(localDateTo) >= 0)) {
                    salary += Integer.valueOf(list[2]) * Integer.valueOf(list[3]);
                }
            }
            report = report.append("\n" + names[i] + " - " + Integer.toString(salary));
            salary = 0;
        }
        return new String(report);
    }
}
