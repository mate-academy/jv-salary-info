package core.basesyntax;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

public class SalaryInfo {
    private static final int ACCOUNT_DATE = 0;
    private static final int NAME = 1;
    private static final int HOURLY_WAGE = 2;
    private static final int HOURS_WORKED = 3;
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
        StringBuilder result = new StringBuilder("Отчёт за период " + dateFrom + " - "
                + dateTo + "\n");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date dateStart = format.parse(dateFrom);
            Date dateFinish = format.parse(dateTo);
            if (dateFinish.before(dateStart)) {
                return null;
            }
            int[] totalSalaries = new int[names.length];
            for (int i = 0; i < names.length; i++) {
                for (String row : data) {
                    if (names[i].equals(row.split(" ")[NAME])
                            && format.parse(row.split(" ")[ACCOUNT_DATE]).compareTo(dateStart) >= 0
                            && format.parse(row.split(" ")[ACCOUNT_DATE]).compareTo(dateFinish)
                            <= 0) {
                        totalSalaries[i] += Integer.parseInt(row.split(" ")[HOURLY_WAGE])
                                * Integer.parseInt(row.split(" ")[HOURS_WORKED]);
                    }
                }
            }
            for (int i = 0; i < names.length; i++) {
                result.append(names[i] + " - " + totalSalaries[i] + "\n");
            }
        } catch (ParseException e) {
            System.out.println("Wrong date input");
            e.printStackTrace();
        }
        return result.toString();
    }
}
