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
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names:
     * Сергей
     * Андрей
     * София
     * <p>
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
     * <p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public static String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {
        DateTimeFormatter fomater = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localeFrom = LocalDate.parse(dateFrom, fomater);
        LocalDate localeTo = LocalDate.parse(dateTo, fomater);
        int salary = 0;
        StringBuilder report = new StringBuilder("Отчёт за период "
                + dateFrom + " - "
                + dateTo + "\n");

        if (localeFrom.isAfter(localeTo)) {
            return null;
        }
        for (String name : names) {
            for (String temp : data) {
                String[] dateName = temp.split(" ");
                LocalDate tempData = LocalDate.parse(dateName[0], fomater);
                if ((tempData.isBefore(localeTo)
                        || tempData.equals(localeTo))
                        && dateName[1].equals(name)) {
                    salary += Integer.parseInt(dateName[2]) * Integer.parseInt(dateName[3]);
                }
            }
            report.append(name).append(" - ").append(Integer.toString(salary)).append("\n");
            salary = 0;
        }

        return report.toString();
    }
}
