package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        dateFromLocal = dateFromLocal.minusDays(1);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
        dateToLocal = dateToLocal.plusDays(1);
        String[] dataNew;
        StringBuilder statistic = new StringBuilder();

        for (String name : names) {
            int salary = 0;
            for (String date : data) {
                dataNew = date.split(" ");
                LocalDate dateLocal = LocalDate.parse(dataNew[0], formatter);
                String nameData = dataNew[1];
                if (name.equals(nameData)
                        && dateLocal.isAfter(dateFromLocal) && dateLocal.isBefore(dateToLocal)) {
                    salary = salary + Integer.parseInt(dataNew[2].trim())
                            * Integer.parseInt(dataNew[3].trim());
                }
            }
            statistic.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + statistic;
    }
}
