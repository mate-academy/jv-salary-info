package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        FormatStyle fs = FormatStyle.SHORT;
        LocalDate dayFrom = LocalDate.parse(dateFrom, DateTimeFormatter.ofLocalizedDate(fs));
        LocalDate dayTo = LocalDate.parse(dateTo, DateTimeFormatter.ofLocalizedDate(fs));
        if (dayTo.compareTo(dayFrom) < 0) {
            return null;
        }
        int[] salaries = new int[names.length];
        for (String datum : data) {
            String[] arguments = datum.split(" ");
            if (arguments.length > 1) {
                if (arguments[0].compareTo(dateFrom) >= 0 && dateTo.compareTo(arguments[0]) >= 0) {
                    int nameIndex = -1;
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(arguments[1])) {
                            nameIndex = i;
                            break;
                        }
                    }
                    if (nameIndex > -1) {
                        salaries[nameIndex] += Integer.parseInt(arguments[2])
                                * Integer.parseInt(arguments[3]);
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
}
