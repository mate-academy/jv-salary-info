package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int ENTRY_DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String entry : data) {
                String[] parts = entry.split(" ");
                String entryDateStr = parts[ENTRY_DATE_INDEX];
                String employeeName = parts[EMPLOYEE_NAME_INDEX];
                int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);

                LocalDate entryDate = LocalDate.parse(entryDateStr, DATE_FORMATTER);
                LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
                LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

                if (employeeName.equals(name) && !entryDate.isBefore(fromDate) 
                        && !entryDate.isAfter(toDate)) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }

            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }
}
