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
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate date1 = LocalDate.parse(dateFrom, formatter);
        LocalDate date2 = LocalDate.parse(dateTo, formatter);
        if (date1.isAfter(date2)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder result = new StringBuilder("Отчёт за период ").append(dateFrom)
                .append(" - ").append(dateTo);
        List<EmployeeWorkdaySummary> summaryObjects = buildSummaryObjects(data);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (EmployeeWorkdaySummary summaryObject : summaryObjects) {
                if (names[i].equals(summaryObject.getPersonName())
                        && isBetween(date1, date2, summaryObject.getDate())) {
                    salary += summaryObject.getHours()
                            * summaryObject.getHourRate();

                }
            }
            result.append("\n").append(buildSummaryObjects(data).get(i).getPersonName())
                    .append(" - ").append(salary);

        }
        return result.toString();
    }

    private List<EmployeeWorkdaySummary> buildSummaryObjects(String[] data) {
        List<EmployeeWorkdaySummary> result = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            String[] arr = data[i].split(" ");
            LocalDate date = LocalDate.parse(arr[0], formatter);
            int hours = Integer.parseInt(arr[2]);
            int salaryPerHour = Integer.parseInt(arr[3]);
            result.add(new EmployeeWorkdaySummary(arr[1], date, hours, salaryPerHour));
        }
        return result;
    }

    private boolean isBetween(LocalDate dateFrom, LocalDate dateTo, LocalDate target) {
        return target.isAfter(dateFrom.minusDays(1)) && target.isBefore(dateTo.plusDays(1));
    }
}
