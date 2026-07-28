package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;

            for (String line : data) {
                String[] info = line.split(" ");
                LocalDate date = LocalDate.parse(info[0], formatter);

                if (info[1].equals(name) && !date.isBefore(from) && !date.isAfter(to)) {
                    salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                }

            }

            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }

        return result.toString();
    }
}
