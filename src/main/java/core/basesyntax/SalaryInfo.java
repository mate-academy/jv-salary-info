package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0;i < names.length; i++) {
            String name = names[i];

            int totalSalary = 0;
            for (String entry : data) {
                String[] entryParts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(entryParts[0], DATE_FORMATTER);

                if (entryParts[1].equals(name) && isDateInRange(entryDate, fromDate, toDate)) {
                    int hoursWorked = Integer.parseInt(entryParts[2]);
                    int hourlyRate = Integer.parseInt(entryParts[3]);
                    totalSalary += hoursWorked * hourlyRate;
                }
            }
            StringBuilder result = report.append(name)
                                         .append(" - ")
                                         .append(totalSalary);
            if (i != names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return report.toString();
    }

    private boolean isDateInRange(LocalDate date, LocalDate fromDate, LocalDate toDate) {
        return !date.isBefore(fromDate) && !date.isAfter(toDate);
    }
}
