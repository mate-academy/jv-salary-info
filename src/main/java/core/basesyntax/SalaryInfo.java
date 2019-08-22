package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * names:
     * Сергей
     * Андрей
     * София
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
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try {
            Date start = date.parse(dateFrom);
            Date end = date.parse(dateTo);
            if (start.after(end)) {
                return null;
            }
            for (String name : names) {
                int salary = 0;
                for (String employee : data) {
                    String[] parts = employee.split(" ");
                    if (parts[1].equals(name)
                            && !(date.parse(parts[0])).before(start)
                            && !(date.parse(parts[0]).after(end))) {
                        salary += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    }
                }
                report.append(name + " - " + salary + "\n");
            }
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return report.toString();
    }
}
