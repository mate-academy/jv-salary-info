package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalEarnings = 0;

            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(parts[0], DATE_FORMATTER);

                LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
                LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

                if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)
                        && parts[1].equals(name)) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    totalEarnings += hours * rate;
                }
            }

            report.append(System.lineSeparator()).append(name).append(" - ").append(totalEarnings);
        }

        return report.toString();
    }
}
