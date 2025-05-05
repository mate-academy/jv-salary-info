package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int WORKING_HOURS_POSITION = 2;
    public static final int INCOME_PER_HOUR_POSITION = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String SPACE = " ";
    public static final String INFO_TEXT = "Report for period ";
    public static final String DASH = " - ";
    public static final String SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || names.length == 0 || data.length == 0) {
            throw new IllegalArgumentException("Invalid input data");
        }
        StringBuilder result = new StringBuilder();
        result.append(INFO_TEXT).append(dateFrom).append(DASH).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String currentData : data) {
                String[] employeeData = currentData.split(SPACE);
                if (employeeData[NAME_POSITION].equals(name)
                        && isInRange(dateFrom, dateTo, employeeData[DATE_POSITION])) {
                    salary = salary + Integer.parseInt(employeeData[WORKING_HOURS_POSITION])
                            * Integer.parseInt(employeeData[INCOME_PER_HOUR_POSITION]);
                }
            }
            result.append(SEPARATOR).append(name).append(DASH).append(salary);
        }
        return result.toString();
    }

    private boolean isInRange(String dateFrom, String dateTo, String targetDate) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);
        LocalDate current = LocalDate.parse(targetDate, formatter);
        return !current.isBefore(dateFromFormatted) && !current.isAfter(dateToFormatted);
    }
}
