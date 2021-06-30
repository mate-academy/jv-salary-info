package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salaryCount = 0;
            for (String datum : data) {
                if (name.equals(datum.substring(11, 11 + name.length()))
                        && !getDate(datum.substring(0, 10)).isBefore(getDate(dateFrom))
                        && !getDate(datum.substring(0, 10)).isAfter(getDate(dateTo))) {
                    int hours = Integer.parseInt(datum.split(" ")[2]);
                    int salary = Integer.parseInt(datum.split(" ")[3]);
                    salaryCount += hours * salary;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salaryCount);
        }
        return result.toString().trim();
    }

    public final LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
