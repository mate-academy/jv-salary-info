package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_FORMAT_LENGTH = 10;
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        int[] earnings = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[0], formatter);
            String currentName = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);

            if (isWithinDateRange(currentDate, fromDate, toDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (currentName.equals(names[i])) {
                        earnings[i] += hours * incomePerHour;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                report.append(names[i]).append(" - ").append(earnings[i]);
            } else {
                report.append(names[i]).append(" - ").append(earnings[i])
                        .append(System.lineSeparator());
            }
        }

        return report.toString();
    }

    private boolean isWithinDateRange(LocalDate currentDate, LocalDate fromDate,
                                      LocalDate toDate) {
        return !currentDate.isBefore(fromDate) && !currentDate.isAfter(toDate);
    }
}
