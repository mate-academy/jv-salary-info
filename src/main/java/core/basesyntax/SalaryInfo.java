package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int WAGES_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] parts = datum.split(" ");
                if (parts[NAME_INDEX].equals(name)) {
                    LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                    if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                        int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                        int wages = Integer.parseInt(parts[WAGES_INDEX]);
                        totalSalary += hoursWorked * wages;
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();
    }
}
