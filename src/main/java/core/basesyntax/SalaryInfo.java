package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginData = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endData = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int count = 0;

            for (String matcher : data) {
                LocalDate date = LocalDate.parse(matcher.split(" ")[0], DATE_TIME_FORMATTER);
                if (name.equals(matcher.split(" ")[1])
                        && (date.isAfter(beginData) && date.isBefore(endData)
                        || date.isEqual(beginData) || date.isEqual(endData))) {
                    count += Integer.parseInt(matcher.split(" ")[2])
                            * Integer.parseInt(matcher.split(" ")[3]);
                }
            }
            builder.append("\n").append(name).append(" - ").append(count);
        }
        return builder.toString();
    }

}
