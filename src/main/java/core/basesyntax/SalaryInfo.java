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
            throws IllegalDateParametersException, ParseException {
        Date dateFromDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
        Date dateToDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        String[] parsedData = new String[4];
        int[] salary = new int[names.length];

        if (dateFromDate.after(dateToDate)) {
            throw new IllegalDateParametersException();
        }

        for (int i = 0; i < data.length; i++) {
            parsedData = data[i].split(" ");
            Date dateTemp = null;
            dateTemp = new SimpleDateFormat("dd.MM.yyyy").parse(parsedData[0]);
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(parsedData[1])) {
                    if (dateTemp.equals(dateFromDate) || dateTemp.equals(dateToDate)
                            || dateTemp.after(dateFromDate) && dateTemp.before(dateToDate)) {
                        salary[j] = salary[j] + (Integer.parseInt(parsedData[2])
                                * Integer.parseInt(parsedData[3]));
                    }
                }
            }
        }

        return buildReport(names, salary, dateFrom, dateTo);
    }

    private String buildReport(String[] names, int[] salary, String dateFrom, String dateTo) {
        String report = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (int i = 0; i < names.length; i++) {
            report = report + names[i] + " - " + salary[i] + "\n";
        }
        return report;
    }

    public static void main(String[] args) {
        String[] roles = {"Сергей", "Андрей", "София"};
        String[] script = {
                "25.04.2019 Сергей 60 50",
                "25.04.2019 Андрей 3 200",
                "25.04.2019 София 10 100",

                "26.04.2019 Андрей 3 200",
                "26.04.2019 София 9 100",

                "27.04.2019 Сергей 7 100",
                "27.04.2019 София 3 80",
                "27.04.2019 Андрей 8 100"
        };
        SalaryInfo salaryInfo = new SalaryInfo();
        try {
            salaryInfo.getSalaryInfo(roles, script, "27.04.2019", "25.04.2019");
        } catch (IllegalDateParametersException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
