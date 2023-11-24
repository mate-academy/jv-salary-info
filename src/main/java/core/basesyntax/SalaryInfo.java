package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE_SPLITTER = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            builder.append(name)
                    .append(" - ")
                    .append(calculateSalary(name, data, localDateFrom, localDateTo))
                    .append(System.lineSeparator());
        }
        return builder.delete(builder.length()
                - System.lineSeparator().length(), builder.length()).toString();
    }

    private int calculateSalary(String name, String[] data, LocalDate dateFrom, LocalDate dateTo) {
        int salary = 0;
        for (String datum : data) {
            String[] parts = datum.split(SPACE_SPLITTER);
            LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_TIME_FORMATTER);
            String employeeName = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
            if (employeeName.equals(name)
                    && !date.isBefore(dateFrom)
                    && !date.isAfter(dateTo)) {
                salary += hours * incomePerHour;
            }
        }
        return salary;
    }
}
