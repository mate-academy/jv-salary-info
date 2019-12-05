package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        if (!checkDate(dateFrom, dateTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder report = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(name)) {
                    String[] dataRecord = data[i].split(" ");
                    if (checkDate(dateFrom, dataRecord[0]) && checkDate(dataRecord[0], dateTo)) {
                        salary += Integer.parseInt(dataRecord[2]) * Integer.parseInt(dataRecord[3]);
                    }
                }
            }
            report.append("\n").append(name).append(" - ").append(salary);
        }

        return report.toString() + "\n";
    }

    public static boolean checkDate(String dateFrom, String dateTo) {
        try {
            Date dateToF = dateFormat.parse(dateTo);
            Date dateFromF = dateFormat.parse(dateFrom);
            /*System.out.println(dateFromF);
            System.out.println(dateToF);*/
            return dateFromF.compareTo(dateToF) <= 0;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*public static void main(String[] args) {
        try {

            System.out.println(getSalaryInfo(ROLES, SCRIPT_ARRAY,
                    "27.03.2019", "26.04.2019"));

        } catch (IllegalDateParametersException e) {
            e.printStackTrace();
        }

        //System.out.println(checkDate("27.03.2019", "15.04.2019"));
    }

    private static final String[] ROLES = {"Сергей", "Андрей", "София"};
    private static final String[] SCRIPT_ARRAY = {
            "25.04.2019 Сергей 60 50",
            "25.04.2019 Андрей 3 200",
            "25.04.2019 София 10 100",

            "26.04.2019 Андрей 3 200",
            "26.04.2019 София 9 100",

            "27.04.2019 Сергей 7 100",
            "27.04.2019 София 3 80",
            "27.04.2019 Андрей 8 100"
    };*/
}
