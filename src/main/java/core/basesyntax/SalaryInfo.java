package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] employeeNames, String[] workData,
                                String dateFrom, String dateTo) {
        LocalDate fromDate;
        LocalDate toDate;

        try {
            fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
            toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Incorrect date format", e);
        }

        int[] employeeSalaries = new int[employeeNames.length];

        for (String entry : workData) {
            String[] parts = entry.split(" ");
            try {
                LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
                if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
                    String employeeName = parts[EMPLOYEE_NAME_INDEX];
                    int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                    int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);

                    for (int i = 0; i < employeeNames.length; i++) {
                        if (employeeNames[i].equals(employeeName)) {
                            employeeSalaries[i] += hoursWorked * hourlyRate;
                            break;
                        }
                    }
                }
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Incorrect date format in data", e);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < employeeNames.length; i++) {
            report.append(System.lineSeparator())
                    .append(employeeNames[i]).append(" - ").append(employeeSalaries[i]);
        }
        return report.toString();
    }
}
