package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final String REPORT_HEADER = "Report for period ";
    private static final String DATE_SEPARATOR = " - ";
    private static final String NAME_SALARY_SEPARATOR = " - ";
    private static final String DATA_SEPARATOR = " ";

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder();
        report.append(REPORT_HEADER)
                .append(dateFrom)
                .append(DATE_SEPARATOR)
                .append(dateTo);

        for (String name : names) {
            int salary = calculateSalary(name, data, startDate, endDate);

            report.append(System.lineSeparator())
                    .append(name)
                    .append(NAME_SALARY_SEPARATOR)
                    .append(salary);
        }

        return report.toString();
    }

    private int calculateSalary(String name, String[] data,
                                LocalDate startDate, LocalDate endDate) {
        int salary = 0;

        for (String record : data) {
            String[] recordParts = record.split(DATA_SEPARATOR);

            LocalDate workDate = LocalDate.parse(recordParts[DATE_INDEX], DATE_FORMATTER);
            String employeeName = recordParts[NAME_INDEX];
            int hours = Integer.parseInt(recordParts[HOURS_INDEX]);
            int rate = Integer.parseInt(recordParts[RATE_INDEX]);

            if (employeeName.equals(name)
                    && !workDate.isBefore(startDate)
                    && !workDate.isAfter(endDate)) {
                salary += hours * rate;
            }
        }

        return salary;
    }
}
