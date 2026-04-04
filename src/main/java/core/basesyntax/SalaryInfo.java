package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] part = datum.split(" ");
                LocalDate localDate = LocalDate.parse(part[0], formatter);
                String result = part[1];
                if (result.equals(name)
                        && !localDate.isBefore(localDateFrom)
                        && !localDate.isAfter(localDateTo)) {
                    salary += Integer.parseInt(part[3])
                            * Integer.parseInt(part[2]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
