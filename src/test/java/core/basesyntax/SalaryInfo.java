package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                String employeeName = recordParts[1];
                int hoursWorked = Integer.parseInt(recordParts[2]);
                int hourlyRate = Integer.parseInt(recordParts[3]);

                if (employeeName.equals(name) && !workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }
            report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
