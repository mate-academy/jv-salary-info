package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int salarySum = 0;
            for (String date : data) {
                String[] splittedDate = date.split(" ");
                if (name.equals(date.substring(11, 11 + name.length()))
                        && !parseDate(splittedDate[0]).isBefore(parseDate(dateFrom))
                        && !parseDate(splittedDate[0]).isAfter(parseDate(dateTo))) {
                    int hours = Integer.parseInt(splittedDate[2]);
                    int salary = Integer.parseInt(splittedDate[3]);
                    salarySum += (hours * salary);
                }
            }
            report.append(name).append(" - ").append(salarySum)
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }

    public final LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.parse(date, formatter);
    }
}
