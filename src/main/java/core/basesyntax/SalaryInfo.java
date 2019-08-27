package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период<p>
     *
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019<p>
     *
     * names:
     * Сергей
     * Андрей
     * София<p>
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
     * 26.04.2019 Сергей 11 50<p>
     *
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.mm.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Hashtable<String, Integer> employees = new Hashtable<>();
        for (String value : names) {
            employees.put(value, 0);
        }

        try {
            Date beginsFrom = DATE_FORMAT.parse(dateFrom);
            Date endsOn = DATE_FORMAT.parse(dateTo);
            if (beginsFrom.after(endsOn)) {
                return null;
            }
            for (String employeeInfo : data) {
                Date currentDate = DATE_FORMAT.parse(employeeInfo.split(" ")[0]);
                String name = employeeInfo.split(" ")[1];
                int bet = Integer.parseInt(employeeInfo.split(" ")[2]);
                int salary = Integer.parseInt(employeeInfo.split(" ")[3]);
                if (currentDate.after(beginsFrom) && (currentDate.before(endsOn)
                        || currentDate.equals(endsOn))) {
                    employees.put(name, employees.get(name) + bet * salary);
                }
            }
        } catch (ParseException e) {
            System.out.print(e.getMessage());
        }

        StringBuilder result =
                new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + '\n');
        for (String value : names) {
            result.append(value + " - " + employees.get(value) + '\n');
        }
        return result.toString();
    }
}
