package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int counter = 0;
            for (String matcher : data) {
                LocalDate date = LocalDate.parse(matcher.split(" ")[0], FORMATTER);
                if (name.equals(matcher.split(" ")[1])
                        && (date.isAfter(beginFrom) && date.isBefore(endTo)
                        || date.isEqual(beginFrom) || date.isEqual(endTo))) {
                    counter += Integer.parseInt(matcher.split(" ")[2])
                            * Integer.parseInt(matcher.split(" ")[3]);
                }
            }
            builder.append("\n").append(name).append(" - ").append(counter);
        }
        return builder.toString();
    }
}
