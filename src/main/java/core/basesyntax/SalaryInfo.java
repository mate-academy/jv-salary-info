package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ENTRY_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int INCOME_INDEX = 2;
    private static final int HOURS_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalEarnings = 0;

            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(parts[ENTRY_DATE_INDEX], DATE_FORMATTER);

                LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
                LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

                if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)
                        && parts[NAME_INDEX].equals(name)) {
                    int hours = Integer.parseInt(parts[HOURS_INDEX]);
                    int rate = Integer.parseInt(parts[INCOME_INDEX]);
                    totalEarnings += hours * rate;
                }
            }

            report.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(totalEarnings);
        }

        return report.toString();
    }
}
