package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DateTimeException, NumberFormatException {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        if (from.isAfter(to)) {
            throw new DateTimeException("Invalid period");
        }
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name: names) {
            int amount = 0;
            for (String record: data) {
                String[] parts = record.split(" ");
                if (parts.length != 4) {
                    continue;
                }
                if (!name.equals(parts[1])) {
                    continue;
                }
                LocalDate recordDate = LocalDate.parse(parts[0], formatter);
                if (from.isAfter(recordDate) || to.isBefore(recordDate)) {
                    continue;
                }
                amount += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(amount);
        }
        return result.toString();
    }
}
