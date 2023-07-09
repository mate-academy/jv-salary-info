package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int DAY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(from.format(FORMATTER))
                .append(" - ")
                .append(to.format(FORMATTER));

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] cols = data[j].split("\\s+");
                LocalDate date = LocalDate.parse(cols[DATE_INDEX], FORMATTER);
                String name = cols[NAME_INDEX];
                int hours = Integer.parseInt(cols[HOUR_INDEX]);
                int salaryByHour = Integer.parseInt(cols[DAY_INDEX]);
                if (names[i].equals(name) && between(date, from, to)) {
                    salary += salaryByHour * hours;
                }
            }
            builder.append("\n")
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return String.valueOf(builder);
    }

    public static boolean between(LocalDate date, LocalDate from, LocalDate to) {
        return date.isAfter(from) && date.isBefore(to) || date.isEqual(from) || date.isEqual(to);
    }
}
