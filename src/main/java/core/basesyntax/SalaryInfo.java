package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int RATE_INDEX = 2;
    private static final int TIME_INDEX = 3;
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String REPORT_TITLE = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final String WHITESPACE = " ";
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder()
                .append(REPORT_TITLE)
                .append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo);
        for (String name : names) {
            reportBuilder.append(System.lineSeparator());
            reportBuilder.append(name).append(SEPARATOR);
            int sumSalary = calculateSumSalaryForEmployee(name, data, dateFrom, dateTo);
            reportBuilder.append(sumSalary);
        }
        return reportBuilder.toString();
    }

    private int calculateSumSalaryForEmployee(
            String name, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, timeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, timeFormatter);
        int sumSalary = 0;
        for (String entry : data) {
            String[] parts = entry.split(WHITESPACE);
            String employeeName = parts[NAME_INDEX];
            if (employeeName.equals(name)) {
                sumSalary += calculateSalaryForDay(parts, localDateFrom, localDateTo);
            }
        }
        return sumSalary;
    }

    private int calculateSalaryForDay(
            String[] parts, LocalDate localDateFrom, LocalDate localDateTo) {
        LocalDate workDay = LocalDate.parse(parts[DATE_INDEX], timeFormatter);
        if (!workDay.isAfter(localDateTo) && !workDay.isBefore(localDateFrom)) {
            int rate = Integer.parseInt(parts[RATE_INDEX]);
            int time = Integer.parseInt(parts[TIME_INDEX]);
            return rate * time;
        }
        return 0;
    }
}
