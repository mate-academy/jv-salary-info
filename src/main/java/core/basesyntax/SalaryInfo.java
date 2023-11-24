package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DEFAULT_SALARY = 0;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private static final String REPORT_HEADER = "Report for period";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = " - ";
    private static final String SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String fromDate, String toDate) {
        LocalDate from = LocalDate.parse(fromDate, DATE_FORMAT);
        LocalDate to = LocalDate.parse(toDate, DATE_FORMAT);
        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(SEPARATOR);
            LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMAT);
            String name = parts[NAME_INDEX];
            int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
            int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);

            if ((entryDate.isAfter(from) || entryDate.isEqual(from))
                    && (entryDate.isBefore(to) || entryDate.isEqual(to))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        int salary = salaries[i];
                        salary += hoursWorked * hourlyRate;
                        salaries[i] = salary;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder(REPORT_HEADER
                + " " + fromDate + DELIMITER + toDate
                + LINE_SEPARATOR);
        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(DELIMITER)
                    .append(salaries[i] == DATE_INDEX ? DEFAULT_SALARY : salaries[i])
                    .append(LINE_SEPARATOR);
        }

        return report.toString().trim();
    }
}
