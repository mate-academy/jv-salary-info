package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        int[] employeeSalaries = new int[names.length];
        for (String entry : data) {
            String[] parts = entry.split(" ");
            String entryDateStr = parts[DATE_INDEX];
            String employeeName = parts[NAME_INDEX];
            int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
            int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
            LocalDate entryDate = LocalDate.parse(entryDateStr, DATE_FORMATTER);
            if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        int salary = hoursWorked * incomePerHour;
                        employeeSalaries[i] += salary;
                        break;
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(employeeSalaries[i]);
        }
        return result.toString();
    }
}
