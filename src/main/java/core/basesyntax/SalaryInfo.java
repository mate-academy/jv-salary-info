package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFromString,
                                String dateToString)
            throws DateTimeException, NumberFormatException {
        LocalDate dateFrom = LocalDate.parse(dateFromString, formatter);
        LocalDate dateTo = LocalDate.parse(dateToString, formatter);
        if (dateFrom.isAfter(dateTo)) {
            throw new DateTimeException("Invalid period");
        }
        StringBuilder result = new StringBuilder("Report for period ").append(dateFromString)
                .append(" - ").append(dateToString);
        for (String name: names) {
            int salary = 0;
            for (String record: data) {
                String[] parts = record.split(" ");
                if (parts.length != 4) {
                    continue;
                }
                if (!name.equals(parts[1])) {
                    continue;
                }
                LocalDate recordDate = LocalDate.parse(parts[0], formatter);
                if (dateFrom.isAfter(recordDate) || dateTo.isBefore(recordDate)) {
                    continue;
                }
                salary += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
