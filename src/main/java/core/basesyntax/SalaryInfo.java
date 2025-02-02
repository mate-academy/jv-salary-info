package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String REPORT_HEADER = "Report for period ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SPACE = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), formatter);

        StringBuilder report = new StringBuilder();
        report.append(REPORT_HEADER)
                .append(dateFrom).append(" - ").append(dateTo).append(LINE_SEPARATOR);

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] recordParts = record.split(SPACE);
                LocalDate recordDate = LocalDate.parse(recordParts[DATE_INDEX], formatter);
                String employeeName = recordParts[NAME_INDEX];
                int hoursWorked = Integer.parseInt(recordParts[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(recordParts[RATE_INDEX]);

                if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)
                        && employeeName.equals(name)) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }

            report.append(name).append(" - ").append(totalSalary).append(LINE_SEPARATOR);
        }

        return report.toString().trim();
    }
}
