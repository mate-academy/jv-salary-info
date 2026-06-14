package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        int[] salary = new int[names.length];

        for (String row : data) {
            String[] parts = row.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
            if (currentDate.isBefore(from) || currentDate.isAfter(to)) {
                continue;
            }

            String name = parts[NAME_INDEX];

            int index = -1;
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    index = i;
                    break;
                }
            }

            int hours = Integer.parseInt(parts[HOUR_INDEX]);
            int rate = Integer.parseInt(parts[RATE_INDEX]);
            salary[index] += hours * rate;
        }

        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(salary[i]);

            if (i < names.length - 1) {
                builder.append(System.lineSeparator());
            }

        }
        return builder.toString();
    }
}
