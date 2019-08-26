package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     *
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     *
     * names:
     * Сергей
     * Андрей
     * София
     *
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     *
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dayFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dayTo = LocalDate.parse(dateTo, formatter);
        if (dayTo.compareTo(dayFrom) < 0) {
            return null;
        }
        int[] salaries = new int[names.length];
        for (String datum : data) {
            String[] arguments = datum.split(" ");
            if (arguments.length > 1) {
                LocalDate date = LocalDate.parse(arguments[DATE], formatter);
                if (dateIsBetween(dayFrom, dayTo, date)) {
                    int nameIndex = -1;
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(arguments[NAME])) {
                            nameIndex = i;
                            break;
                        }
                    }
                    if (nameIndex >= 0) {
                        salaries[nameIndex] += Integer.parseInt(arguments[HOURS])
                                * Integer.parseInt(arguments[SALARY]);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder("Отчёт за период ");
        sb.append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(" - ").append(salaries[i]).append("\n");
        }
        return sb.toString();
    }

    private boolean dateIsBetween(LocalDate dateFrom, LocalDate dateTo, LocalDate date) {
        return date.compareTo(dateFrom) >= 0 && dateTo.compareTo(date) >= 0;
    }
}
