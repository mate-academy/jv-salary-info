package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();

        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = calculateTotalSalaryForEmployee(name, data, fromDate, toDate);
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }

    private int calculateTotalSalaryForEmployee(String employeeName, String[] data,
                                                LocalDate fromDate, LocalDate toDate) {
        int totalSalary = 0;

        for (String inception : data) {
            String[] parts = inception.split(SEPARATOR);
            String entryDateOfEmployee = parts[DATE_INDEX];
            String entryName = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int income = Integer.parseInt(parts[INCOME_INDEX]);

            LocalDate entryDate = LocalDate.parse(entryDateOfEmployee, DATE_FORMATTER);

            if (entryName.equals(employeeName) && !entryDate.isBefore(fromDate)
                    && !entryDate.isAfter(toDate)) {
                totalSalary += (hours * income);
            }
        }
        return totalSalary;
    }
}
