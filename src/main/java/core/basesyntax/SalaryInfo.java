package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate start = LocalDate.parse(dateFrom, f).minusDays(1);
        final LocalDate end = LocalDate.parse(dateTo, f).plusDays(1);
        int d = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            for (String datum : data) {
                String[] splitArray = datum.split(" ");
                if (splitArray[1].equals(name)
                        && (LocalDate.parse(splitArray[0], f).isAfter(start)
                        && LocalDate.parse(splitArray[0], f).isBefore(end))) {
                    d += Integer.parseInt(splitArray[2]) * Integer.parseInt(splitArray[3]);
                }
            }
            builder.append(name).append(" - ")
                    .append(d).append(System.lineSeparator());
            d = 0;
        }
        return builder.toString().trim();
    }
}
