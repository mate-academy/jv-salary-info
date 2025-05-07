package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salaryCount = 0;
            for (String datum : data) {
                String[] datumParts = datum.split(" ");
                if (name.equals(datumParts[1])
                        && !getDate(datumParts[0]).isBefore(getDate(dateFrom))
                        && !getDate(datumParts[0]).isAfter(getDate(dateTo))) {
                    int hours = Integer.parseInt(datum.split(" ")[2]);
                    int salary = Integer.parseInt(datum.split(" ")[3]);
                    salaryCount += hours * salary;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salaryCount);
        }
        return report.toString().trim();
    }

    public final LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
