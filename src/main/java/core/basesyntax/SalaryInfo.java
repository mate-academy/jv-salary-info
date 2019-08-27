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
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * </p>
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
     * </p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder allSalary = new StringBuilder();
        allSalary.append("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        final int workHoursPos = 2;
        final int perHourPos = 1;
        final int workDatePos = 0;
        final int namePos = 1;

        try {
            Date minDate = format.parse(dateFrom);
            Date maxDate = format.parse(dateTo);
            if (minDate.compareTo(maxDate) > 0) {
                return null;
            }

            for (int i = 0; i < names.length; i++) {
                int [] salary = new int[names.length];
                for (String s : data) {
                    String[] workInfo = s.split(" ");
                    int workHours = Integer.parseInt(workInfo[workInfo.length - workHoursPos]);
                    int perHour = Integer.parseInt(workInfo[workInfo.length - perHourPos]);
                    Date workDate = format.parse(workInfo[workDatePos]);
                    if (workDate.compareTo(minDate) >= 0 && workDate.compareTo(maxDate) <= 0) {
                        salary[i] += workInfo[namePos].equals(names[i]) ? workHours * perHour : 0;
                    }
                }
                allSalary.append(names[i] + " - " + salary[i] + "\n");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return allSalary.toString();
    }
}
