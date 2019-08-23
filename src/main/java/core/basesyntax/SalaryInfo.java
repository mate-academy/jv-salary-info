package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

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
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate reportingDate;
        int amount;
        int rate;
        int hours;
        String[] parsedData;

        if (to.isBefore(from)) {
            return null;
        }
        result.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo)
            .append("\n");

        for (String name : names) {
            amount = 0;
            for (String s : data) {
                parsedData = s.split(" ");
                if (name.equals(parsedData[1])) {
                    reportingDate = LocalDate
                        .parse(parsedData[0], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    if (reportingDate.isAfter(from.minusDays(1)) && reportingDate
                            .isBefore(to.plusDays(1))) {
                        hours = Integer.parseInt(parsedData[2]);
                        rate = Integer.parseInt(parsedData[3]);
                        amount += rate * hours;
                    }
                }
            }
            result.append(name).append(" - ").append(amount).append("\n");
        }
        return result.toString();
    }
}
