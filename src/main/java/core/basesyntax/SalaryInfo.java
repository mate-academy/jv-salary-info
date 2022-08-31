package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        final int dateIndex = 0;
        final int nameIndex = 1;
        final int hoursIndex = 2;
        final int salaryIndex = 3;

        result.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (final String name : names) {
            result.append(System.lineSeparator());
            int salary = 0;

            for (final String datum : data) {
                String[] datumSplit = datum.split(" ");
                LocalDate current = LocalDate.parse(datumSplit[dateIndex], formatter);

                if (name.equals(datumSplit[nameIndex])) {
                    if (
                            (current.isAfter(from) || current.isEqual(from))
                                    && (current.isBefore(to) || current.isEqual(to))
                    ) {
                        salary += Integer.parseInt(datumSplit[hoursIndex])
                                * Integer.parseInt(datumSplit[salaryIndex]);
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
