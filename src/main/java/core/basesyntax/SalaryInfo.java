package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder appender = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToParsed = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (String name : names) {
            int sum = 0;

            for (String row : data) {
                String[] info = row.split(" ");

                if (!info[1].equals(name)) {
                    continue;
                }

                LocalDate date = LocalDate.parse(info[0], DATE_TIME_FORMATTER);
                boolean gte = date.isAfter(dateFromParsed) || date.isEqual(dateFromParsed);
                boolean lessThenOrEqual = date.isBefore(dateToParsed) || date.isEqual(dateToParsed);
                boolean dateBetween = gte && lessThenOrEqual;

                if (!dateBetween) {
                    continue;
                }

                sum += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
            }

            appender.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return appender.toString();
    }
}
