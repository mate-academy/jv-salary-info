package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date beginOfPeriod = null;
        Date endOfPeriod = null;
        try {
            beginOfPeriod = format.parse(dateFrom);
            endOfPeriod = format.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String stringResult = String.format("Отчёт за период %s - %s\n", dateFrom, dateTo);
        for (String name: names) {
            int personSalary = 0;
            for (String daraRow: data) {
                String[] arrayOfPersonReport = daraRow.split(" ");
                Date dateToCheck = null;
                try {
                    dateToCheck = format.parse(arrayOfPersonReport[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (name.equals(arrayOfPersonReport[1])
                        && !beginOfPeriod.after(dateToCheck)
                        && !endOfPeriod.before(dateToCheck)) {
                    personSalary += Integer.valueOf(arrayOfPersonReport[2])
                            * Integer.valueOf(arrayOfPersonReport[3]);
                }
            }
            stringResult += String.format("%s - %d\n", name, personSalary);
        }
        if (beginOfPeriod.after(endOfPeriod)) {
            return null;
        }
        return stringResult;
    }
}
