package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    /*public static void main(String[] args) {
        String date = "01.04.2019";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println(LocalDate.parse(date, formatter));
    }*/
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (LocalDate.parse(dateFrom, formatter).isAfter(LocalDate.parse(dateTo, formatter))) {
            return null;
        }
        StringBuilder resultText = new StringBuilder();
        resultText.append("Отчёт за период ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        int salaryCounter = 0;
        for (String name : names) {
            for (String dataRow : data) {
                if (dataRow.contains(name) && checkDateRange(dataRow, dateFrom, dateTo)) {
                    int workedHours = Integer.parseInt(dataRow.split(" ")[2]);
                    int salaryPerHour = Integer.parseInt(dataRow.split(" ")[3]);
                    salaryCounter += workedHours * salaryPerHour;
                }
            }
            resultText.append(name).append(" - ").append(salaryCounter).append("\n");
            salaryCounter = 0;
        }
        return resultText.toString();
    }

    private static boolean checkDateRange(String row, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate rowDate = LocalDate.parse(row.split(" ")[0], formatter);
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate end = LocalDate.parse(dateTo, formatter);
        return (!rowDate.isBefore(start) && !rowDate.isAfter(end));
    }
}
