package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyy";
    private static final String EMPTY_SEPARATOR = " ";
    private static final String REPORT_SEPARATOR = " - ";
    private static final short LINE_DATE = 0;
    private static final short LINE_NAME = 1;
    private static final short LINE_HOURS = 2;
    private static final short LINE_SALARY = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(REPORT_SEPARATOR)
                .append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            report.append(name).append(REPORT_SEPARATOR)
                    .append(calculateSalary(name, data, dateFrom, dateTo))
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private int calculateSalary(String name, String[] data, String fromDate, String toDate) {
        int salary = 0;
        String[] table;
        for (String tableLine : data) {
            table = tableLine.trim().split(EMPTY_SEPARATOR);
            if ((name.equals(table[LINE_NAME]))
                    && (isDateInRange(table[LINE_DATE], fromDate, toDate))) {
                salary += Integer.decode(table[LINE_HOURS]) * Integer.decode(table[LINE_SALARY]);
            }
        }
        return salary;
    }

    private boolean isDateInRange(String lineString, String fromString, String toString) {
        LocalDate lineDate = LocalDate.parse(lineString.trim(), formatter);
        LocalDate fromDate = LocalDate.parse(fromString.trim(), formatter);
        LocalDate toDate = LocalDate.parse(toString.trim(), formatter);
        return lineDate.isAfter(fromDate.minusDays(1))
                && lineDate.isBefore(toDate.plusDays(1));
    }
}
