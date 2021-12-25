package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromPeriod = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToPeriod = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder(" Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            builder.append(name).append(" - ");
            for (String pr : data) {
                String[] array = pr.split(" ");
                if (name.equals(array[1])) {
                    try {
                        LocalDate date = LocalDate.parse(array[0], formatter);
                        if (date.isAfter(dateFromPeriod) && date.isBefore(dateToPeriod)
                                || date.equals(dateFromPeriod) || date.equals(dateToPeriod)) {
                            salary += Integer.parseInt(array[2]) * Integer.parseInt(array[3]);
                        }
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Invalid format for date", e);
                    }
                }
            }
            builder.append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
