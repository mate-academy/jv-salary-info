package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        Calendar calendarFrom = getDate(dateFrom);
        Calendar calendarTo = getDate(dateTo);
        if (calendarFrom.after(calendarTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        String[][] dataSplit = new String[data.length][4];
        for (int i = 0; i < dataSplit.length; i++) {
            dataSplit[i] = data[i].split(" ");
        }

        StringBuilder salaryInfo =
                new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        for (String name : names) {
            int salary = 0;
            for (String[] strings : dataSplit) {
                if (name.equals(strings[1])) {
                    if (!getDate(strings[0]).after(calendarTo)
                            && !getDate(strings[0]).before(calendarFrom)) {
                        salary += Integer.parseInt(strings[2])
                                * Integer.parseInt(strings[3]);
                    }
                }
            }
            salaryInfo.append(name).append(" - ").append(salary).append("\n");
        }
        return salaryInfo.toString();
    }

    public Calendar getDate(String s) {
        String[] split = s.split("\\.");
        int year = Integer.parseInt(split[2]);
        int month = Integer.parseInt(split[1]) - 1;
        int day = Integer.parseInt(split[0]);
        return new GregorianCalendar(year, month, day);
    }
}
