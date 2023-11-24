package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER 
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String ENTRY_SEPARATOR = " ";
    private static final int ENTRY_DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = calculateTotalSalaryForName(name, data, dateFrom, dateTo);
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }

    private int calculateTotalSalaryForName(String name, String[] data, 
            String dateFrom, String dateTo) {
        int totalSalary = 0;

        for (String entry : data) {
            String[] parts = entry.split(ENTRY_SEPARATOR);
            String employeeName = parts[EMPLOYEE_NAME_INDEX];

            if (employeeName.equals(name)) {
                totalSalary += calculateSalaryForEntry(parts, dateFrom, dateTo);
            }
        }

        return totalSalary;
    }

    private int calculateSalaryForEntry(String[] parts, String dateFrom, String dateTo) {
        String entryDateStr = parts[ENTRY_DATE_INDEX];
        int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
        int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);

        LocalDate entryDate = LocalDate.parse(entryDateStr, DATE_FORMATTER);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
            return hoursWorked * hourlyRate;
        }

        return 0; 
    }
}
