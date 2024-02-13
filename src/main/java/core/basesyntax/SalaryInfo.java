package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_TEMPLATE = "Report for period %s - %s%s";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPLOYEE_TEMPLATE = "%s - %d";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate recordDate = LocalDate.parse(recordParts[0], DATE_FORMATTER);
                if ((recordDate.isAfter(startDate)
                        || recordDate.isEqual(startDate))
                        && (recordDate.isBefore(endDate)
                        || recordDate.isEqual(endDate))
                        && recordParts[1].equals(name)) {
                    int workHours = Integer.parseInt(recordParts[2]);
                    int hourlyRate = Integer.parseInt(recordParts[3]);
                    totalSalary += workHours * hourlyRate;
                }
            }
            report.append(String.format(EMPLOYEE_TEMPLATE, name, totalSalary))
                    .append(LINE_SEPARATOR);
        }
        return String.format(REPORT_TEMPLATE, dateFrom, dateTo, LINE_SEPARATOR)
                + report.toString().trim();
    }
}
