package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
            LocalDate toDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

            StringBuilder report = new StringBuilder();
            report.append("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo)
                    .append(System.lineSeparator());

            for (String name : names) {
                int totalSalary = calculateSalary(name, data, fromDate, toDate);
                report.append(name)
                        .append(" - ")
                        .append(totalSalary)
                        .append(System.lineSeparator());
            }

            return report.toString().trim(); // remove whitespace trailing
        } catch (Exception e) {
            return "Invalid date format";
        }
    }

    private int calculateSalary(String name, String[] data,
                                LocalDate fromDate, LocalDate toDate) {
        int totalSalary = 0;

        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(parts[0], DATE_FORMATTER);

                if (parts[1].equals(name) && !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)) {
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int hourlyRate = Integer.parseInt(parts[3]);
                    totalSalary += hoursWorked * hourlyRate;
                }
            } catch (Exception e) {
                // invalid data format
            }
        }

        return totalSalary;
    }
}
