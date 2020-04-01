package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
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
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        if (suitablePeriod(dateFrom, dateTo) == false) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuffer result = new StringBuffer("Отчёт за период " + dateFrom + " - " + dateTo);
        int salary = 0;
        for (String retVal: names) {
            salary = 0;
            for (int i = 0; i < data.length; i++) {
                String [] oneString = data[i].split(" ");
                if (oneString[1].equals(retVal) == true
                        && suitableDate(dateFrom, dateTo, oneString[0]) == true) {
                    salary += Integer.parseInt(oneString[2]) * Integer.parseInt(oneString[3]);
                }
            }
            result.append("\n").append(retVal).append(" - ").append(salary);
        }

        return result.toString();
    }

    public static Date dateConvInData(String data)
            throws ParseException {
        Date docDate = FORMAT.parse(data);
        return docDate;
    }

    public static boolean suitablePeriod(String firstDate, String secondDate)
            throws ParseException {
        if (dateConvInData(firstDate).compareTo(dateConvInData(secondDate)) > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int afterBeforeEqualsDays(String firstDate, String secondDate)
            throws ParseException {
        if (dateConvInData(firstDate).after(dateConvInData(secondDate)) == true) {
            return 1;
        } else if (dateConvInData(firstDate).equals(dateConvInData(secondDate)) == true) {
            return 0;
        } else {
            return -1;
        }
    }

    public static boolean suitableDate(String firstDate, String secondDate, String exactDate)
            throws ParseException {
        if (afterBeforeEqualsDays(firstDate, exactDate) <= 0
                && afterBeforeEqualsDays(secondDate, exactDate) >= 0) {
            return true;
        } else {
            return false;
        }
    }

}
