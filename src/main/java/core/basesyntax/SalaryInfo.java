package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private StringBuilder builder = new StringBuilder();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        for (String entry : data) {
            Entry currentEntry = parseEntry(entry);
            for (int i = 0; i < names.length; i++) {
                if (isEligible(currentEntry, names[i], startDate, endDate)) {
                    salaries[i] += currentEntry.getMoneyEarned();
                }
            }
        }
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return builder.toString();
    }

    private Entry parseEntry(String entry) {
        String[] splitted = entry.split(" ");
        return new Entry(splitted);
    }

    private boolean isWithinRange(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return !(date.isBefore(dateFrom) || date.isAfter(dateTo));
    }

    private boolean isEligible(Entry entry, String name, LocalDate startDate, LocalDate endDate) {
        return entry.getName().equals(name) && isWithinRange(entry.getDate(), startDate, endDate);
    }
}
