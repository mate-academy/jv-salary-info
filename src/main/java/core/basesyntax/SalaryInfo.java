package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder();

        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate workDate = LocalDate.parse(recordParts[0], DATE_FORMATTER);
                String employee = recordParts[1];
                int hoursWorked = Integer.parseInt(recordParts[2]);
                int hoursRate = Integer.parseInt(recordParts[3]);

                if (employee.equals(name)
                        && (workDate.isEqual(startDate) || workDate.isAfter(startDate))
                        && (workDate.isEqual(endDate) || workDate.isBefore(endDate))) {
                    totalSalary += hoursWorked * hoursRate;
                }
            }
            report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
