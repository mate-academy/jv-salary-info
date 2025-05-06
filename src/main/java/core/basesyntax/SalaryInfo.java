package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate to = LocalDate.parse(dateTo.trim(), FORMATTER);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[0], FORMATTER);
                if (parts[1].equals(names[i])
                        && !date.isBefore(from)
                        && !date.isAfter(to)) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    totalSalary += hours * rate;
                }
            }
            if (i < names.length - 1) {
                result.append(names[i]).append(" - ").append(totalSalary)
                        .append(System.lineSeparator());
            } else {
                result.append(names[i]).append(" - ").append(totalSalary);
            }
        }
        return result.toString();
    }
}
