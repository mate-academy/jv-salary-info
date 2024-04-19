package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String[] str;
    private int sum = 0;
    private String separator = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + separator);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (int i = 0; i < names.length; i++) {
            for (String info : data) {
                str = info.split(" ");
                LocalDate date = LocalDate.parse(str[0], DATE_TIME_FORMATTER);
                if (names[i].equals(str[1])
                        && (date.isAfter(localDateFrom) || date.equals(localDateFrom))
                        && (date.isBefore(localDateTo) || date.equals(localDateTo))) {
                    sum += Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }
            }
            if (i < names.length - 1) {
                result.append(names[i]).append(" - ").append(sum).append(separator);
            } else {
                result.append(names[i]).append(" - ").append(sum);
            }
            sum = 0;
        }
        return result.toString();
    }
}
