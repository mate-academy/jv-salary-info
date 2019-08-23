package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {


    private static final int NAME_POSITION = 0;
    private static final int REPORTING_DATE_POSITION = 1;
    private static final int HOURLY_RATE_POSITION = 2;
    private static final int WORKED_HOURS_POSITION = 3;

    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты, определяющие
     * период за который надо вычислить зарплату, первый массив содержит имена сотрудников
     * организации, второй массив информацию о рабочих часах и ставке. Формат данных второго массива
     * следующий: дата, имя сотрудника, количество отработанных часов, ставка за 1 час. Метод должен
     * вернуть отчёт за период, который передали в метод (обе даты включительно) составленный по
     * следующей форме: Отчёт за период #дата_1# - #дата_2# Имя сотрудника - сумма заработанных
     * средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names: Сергей Андрей София
     * <p>
     * data: 26.04.2019 Сергей 60 50 26.04.2019 Андрей 3 200 26.04.2019 Сергей 7 100 26.04.2019
     * София 9 100 26.04.2019 Сергей 11 50 26.04.2019 Андрей 3 200 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100 26.04.2019 Сергей 11 50
     * <p>
     * Пример вывода: Отчёт за период 01.04.2019  - 30.04.2019 Сергей - 1550 Андрей - 600 София -
     * 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        if (to.isBefore(from)) {
            return null;
        }
        result.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo)
            .append("\n");

        for (String name : names) {
            int amount = 0;
            for (String s : data) {
                String[] parsedData = s.split(" ");
                if (name.equals(parsedData[REPORTING_DATE_POSITION])) {
                    LocalDate reportingDate = LocalDate
                            .parse(parsedData[NAME_POSITION], formatter);
                    if (reportingDate.isAfter(from.minusDays(1)) && reportingDate
                            .isBefore(to.plusDays(1))) {
                        int hours = Integer.parseInt(parsedData[HOURLY_RATE_POSITION]);
                        int rate = Integer.parseInt(parsedData[WORKED_HOURS_POSITION]);
                        amount += rate * hours;
                    }
                }
            }
            result.append(name).append(" - ").append(amount).append("\n");
        }
        return result.toString();
    }
}
