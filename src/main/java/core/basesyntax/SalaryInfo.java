package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
     * <p>
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
     * <p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date dateFromParsed = format.parse(dateFrom);
            Date dateToParsed = format.parse(dateTo);
            if (dateToParsed.compareTo(dateFromParsed) < 0) {
                return null;
            }
            HashMap<String, Integer> salaryReport = new HashMap<>();
            String result = "Отчёт за период " + dateFrom + " " + "-" + " " + dateTo + "\n";
            for (int i = 0; i < names.length; i++) {
                salaryReport.put(names[i], 0);
                int subtotal = 0;
                for (int j = 0; j < data.length; j++) {
                    String[] record = data[j].split("\\s");
                    Date dateWork = format.parse(record[0]);
                    if (dateWork.compareTo(dateFromParsed) >= 0
                            && dateWork.compareTo(dateToParsed) <= 0
                            && record[1].equals(names[i])) {
                        subtotal = salaryReport.get(names[i])
                                + Integer.parseInt(record[2]) * Integer.parseInt(record[3]);
                        salaryReport.put(names[i], subtotal);
                    }
                }
                result += names[i] + " " + "-" + " " + subtotal + "\n";
            }
            return result;
        } catch (ParseException e) {
            final String message = "Date is not a date :)";
            System.out.println(message);
            return message;
        }
    }
}
