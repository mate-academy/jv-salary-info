package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,
                dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo,
                dateTimeFormatter);
        long[] salaries = new long[names.length];
        for (String salary : data) {
            String[] info = salary.split(" ");
            LocalDate date = LocalDate.parse(info[DATE_INDEX],
                    dateTimeFormatter);
            String name = info[NAME_INDEX];
            long hours = Long.parseLong(info[HOURS_INDEX]);
            long perHour = Long.parseLong(info[PER_HOUR_INDEX]);
            if ((date.isAfter(localDateFrom) || date.isEqual(localDateFrom))
                    && (date.isBefore(localDateTo) || date.isEqual(localDateTo))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * perHour;
                        break;
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ").append(salaries[i]);
        }
        return builder.toString();
    }
}
