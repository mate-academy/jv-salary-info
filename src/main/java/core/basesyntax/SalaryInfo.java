package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        result.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (final String name : names) {
            result.append(System.lineSeparator());
            int salary = 0;

            for (final String datum : data) {
                String[] datumSplit = datum.split(" ");
                LocalDate current = LocalDate.parse(datumSplit[0], formatter);

                if (name.equals(datumSplit[1])) {
                    if (
                            (current.isAfter(from) || current.isEqual(from))
                                    && (current.isBefore(to) || current.isEqual(to))
                    ) {
                        salary += Integer.parseInt(datumSplit[2]) * Integer.parseInt(datumSplit[3]);
                    }
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(salary);
        }
        return result.toString();
    }
}
