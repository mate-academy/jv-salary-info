package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

        int[] earnings = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(parts[0], DATE_FORMATTER);

            if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
                String name = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        earnings[i] += hours * rate;
                        break;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(earnings[i]);
        }

        return result.toString();
    }
}
