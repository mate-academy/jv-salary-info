package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if ((names == null) || (data == null) || (dateFrom == null) || (dateTo == null)
                || (names.length == 0) || (data.length == 0) || (dateFrom.length() == 0)
                || (dateTo.length() == 0)) {
            return null;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromLD = LocalDate.parse(dateFrom, dtf);
        LocalDate dateToLD = LocalDate.parse(dateTo, dtf);
        if (dateToLD.compareTo(dateFromLD) < 0) {
            return null;
        }

        String[] nameSalary = new String[names.length];
        int countForNameSalary = 0;

        for (int i = 0; i < nameSalary.length; i++) {
            nameSalary[i] = names[i] + " - " + "0";
        }

        for (String str : names) {
            String name = str;
            int salary = 0;

            for (String str2 : data) {
                String[] oneStringOfData = str2.split(" ");
                if (oneStringOfData[1].equals(name)) {
                    LocalDate dateOfString = LocalDate.parse(oneStringOfData[0], dtf);
                    if (dateOfString.isAfter(dateFromLD.minusDays(1))
                            && dateOfString.isBefore(dateToLD.plusDays(1))) {
                        salary += Integer.parseInt(oneStringOfData[2])
                                * Integer.parseInt(oneStringOfData[3]);
                        continue;
                    }
                }
            }

            nameSalary[countForNameSalary++] = name + " - " + salary;

        }
        String result = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (String str : nameSalary) {
            result += str + "\n";
        }
        return result;
    }
}
