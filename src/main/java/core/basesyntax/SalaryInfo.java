package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sums = new int[names.length];
        LocalDate from = parseDate(dateFrom);
        LocalDate to = parseDate(dateTo);

        for (String entry : data) {
            processEntry(entry, names, sums, from, to);
        }

        return generateReport(names, sums, dateFrom, dateTo);
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    private boolean isDateWithinRange(LocalDate entryDate, LocalDate from, LocalDate to) {
        return (entryDate.isEqual(from) || entryDate.isAfter(from))
                && (entryDate.isEqual(to) || entryDate.isBefore(to));
    }

    private void processEntry(String entry, String[] names,
                              int[] sums, LocalDate from, LocalDate to) {
        String[] parts = entry.split(" ");
        LocalDate entryDate = parseDate(parts[DATE_INDEX]);

        if (isDateWithinRange(entryDate, from, to)) {
            String employeeName = parts[NAME_INDEX];
            int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
            int salaryPerHour = Integer.parseInt(parts[SALARY_PER_HOUR_INDEX]);

            addSalaryToEmployee(employeeName, hoursWorked, salaryPerHour, names, sums);
        }
    }

    private void addSalaryToEmployee(String employeeName,
                                     int hoursWorked,
                                     int salaryPerHour, String[] names, int[] sums) {
        for (int j = 0; j < names.length; j++) {
            if (employeeName.equals(names[j])) {
                sums[j] += salaryPerHour * hoursWorked;
            }
        }
    }

    private String generateReport(String[] names, int[] sums, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(sums[i]);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
