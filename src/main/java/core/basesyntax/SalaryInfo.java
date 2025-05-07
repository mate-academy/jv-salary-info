package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String entry : data) {
                String[] entryParts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(entryParts[DATE_INDEX], DATE_FORMATTER);

                if (entryParts[NAME_INDEX].equals(name)
                        && isDateInRange(entryDate, fromDate, toDate)) {
                    int hoursWorked = Integer.parseInt(entryParts[HOURS_WORKED_INDEX]);
                    int hourlyRate = Integer.parseInt(entryParts[HOURLY_RATE_INDEX]);
                    totalSalary += hoursWorked * hourlyRate;
                }
            }
            report
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return report.toString();
    }

    private boolean isDateInRange(LocalDate date, LocalDate fromDate, LocalDate toDate) {
        return !date.isBefore(fromDate) && !date.isAfter(toDate);
    }
}
