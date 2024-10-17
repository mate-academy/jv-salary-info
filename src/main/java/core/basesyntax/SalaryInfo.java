package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

        int[] salaries = new int[names.length]; 

        for (String record : data) {
            String[] recordParts = record.split(" ");
            LocalDate workDate = LocalDate.parse(recordParts[0], DATE_FORMATTER);
            String employeeName = recordParts[1];
            int hoursWorked = Integer.parseInt(recordParts[2]);
            int payPerHour = Integer.parseInt(recordParts[3]);

            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += hoursWorked * payPerHour; 
                    }
                }
            }
        }

        return buildReport(names, salaries, dateFrom, dateTo);
    }

    private String buildReport(String[] names, int[] salaries, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom.trim()).append(" - ").append(dateTo.trim()).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
