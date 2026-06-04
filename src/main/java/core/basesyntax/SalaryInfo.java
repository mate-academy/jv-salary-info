package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period ";
    private static final String DATE_SEPARATOR = " - ";
    private static final String NAME_SALARY_SEPARATOR = " - ";
    private static final String DATA_SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMAT);

        StringBuilder report = new StringBuilder(REPORT_HEADER)
                .append(dateFrom)
                .append(DATE_SEPARATOR)
                .append(dateTo);

        for (String name : names) {
            int salary = calculateSalaryForName(name, data, from, to);

            report.append(System.lineSeparator())
                    .append(name)
                    .append(NAME_SALARY_SEPARATOR)
                    .append(salary);
        }

        return report.toString();
    }

    private int calculateSalaryForName(String name, String[] data, LocalDate from, LocalDate to) {
        int salary = 0;

        for (String record : data) {
            String[] fields = record.split(DATA_SEPARATOR);
            LocalDate currentDate = LocalDate.parse(fields[DATE_INDEX], DATE_FORMAT);

            if (name.equals(fields[NAME_INDEX])
                    && !currentDate.isBefore(from)
                    && !currentDate.isAfter(to)) {
                int hours = Integer.parseInt(fields[HOURS_INDEX]);
                int rate = Integer.parseInt(fields[RATE_INDEX]);

                salary += hours * rate;
            }
        }

        return salary;
    }
}
