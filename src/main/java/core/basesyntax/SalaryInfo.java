package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE_POSITION = 0;
    public static final int HOURS_POSITION = 2;
    public static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        LocalDate dayFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dayTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int counter = 0;
            for (String datum : data) {
                String[] parts = datum.split(" ");
                if (!datum.contains(name)) {
                    continue;
                }
                LocalDate workingDay = LocalDate.parse(parts[DATE_POSITION], FORMATTER);
                if (workingDay.isAfter(dayFrom) && workingDay.isBefore(dayTo)
                        || workingDay.isEqual(dayFrom) || workingDay.isEqual(dayTo)) {
                    counter += Integer.parseInt(parts[HOURS_POSITION])
                            * Integer.parseInt(parts[SALARY_POSITION]);
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(counter)
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
