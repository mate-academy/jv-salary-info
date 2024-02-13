package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_TEMPLATE = "Report for period %s - %s%s";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPLOYEE_TEMPLATE = "%s - %d";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        generateSalaryReportForPeriod(names, data, startDate, endDate, report);
        return String.format(REPORT_TEMPLATE, dateFrom, dateTo, LINE_SEPARATOR)
                + report.toString().trim();
    }

    private static void generateSalaryReportForPeriod(String[] names,
                                                      String[] data, LocalDate startDate,
                                                      LocalDate endDate, StringBuilder report) {
        for (String name : names) {
            int totalSalary = 0;
            totalSalary = getTotalSalary(data, name, startDate, endDate, totalSalary);
            report.append(String.format(EMPLOYEE_TEMPLATE, name, totalSalary))
                    .append(LINE_SEPARATOR);
        }
    }

    private static int getTotalSalary(String[] data, String name,
                                      LocalDate startDate, LocalDate endDate, int totalSalary) {
        for (String record : data) {
            String[] recordParts = record.split(" ");
            LocalDate recordDate = LocalDate.parse(recordParts[DATE_INDEX], DATE_FORMATTER);
            if ((recordDate.isAfter(startDate)
                    || recordDate.isEqual(startDate))
                    && (recordDate.isBefore(endDate)
                    || recordDate.isEqual(endDate))
                    && recordParts[NAME_INDEX].equals(name)) {
                int workHours = Integer.parseInt(recordParts[WORK_HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(recordParts[HOURLY_RATE_INDEX]);
                totalSalary += workHours * hourlyRate;
            }
        }
        return totalSalary;
    }
}
