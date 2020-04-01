package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
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
            throws IllegalAccessException, ParseException {

        if (!compareDates(dateFrom,dateTo)) {
            throw new IllegalAccessException("Wrong parameters");
        }

        StringBuilder sb = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + '\n');
        ArrayList<String[]> dataToArray = new ArrayList<>();

        for (String s : data) {
            if (compareDates(dateFrom,s.substring(0,11))
                    && compareDates(s.substring(0,11),dateTo)) {
                dataToArray.add(s.split(" "));
            }
        }

        int[] money = new int[names.length];
        for (int m: money) {
            m = 0;
        }
        for (int i = 0; i < names.length; i++) {
            for (String[] dataArray : dataToArray) {
                if (names[i].equals(dataArray[1])) {
                    money[i] += Integer.parseInt(dataArray[2]) + Integer.parseInt(dataArray[3]);
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(" - ").append(money[i]).append('\n');
        }
        return sb.toString();
    }

    public boolean compareDates(String d1, String d2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
        Date date1 = sdf.parse(d1);
        Date date2 = sdf.parse(d2);
        return date1.compareTo(date2) < 0;
    }
}
