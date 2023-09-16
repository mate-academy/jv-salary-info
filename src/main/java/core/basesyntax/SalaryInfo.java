package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate fromDate = null;
        LocalDate toDate = null;
        try {
            fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
            toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format");
        }
        int[] employeeSalaries = new int[names.length];
        for (String entry : data) {
            String[] parts = entry.split(" ");
            String entryDateStr = parts[0];
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);
            try {
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
            } catch (Exception e) {
                throw new RuntimeException("Invalid date format");
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
