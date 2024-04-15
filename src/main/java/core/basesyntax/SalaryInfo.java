package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_FORMAT_LENGTH = 10;
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        int[] earnings = this.getEarnings(names, data, fromDate, toDate);

        //report creation
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(earnings[i]);
        }

        return report.toString();
    }

    private boolean isWithinDateRange(LocalDate currentDate, LocalDate fromDate,
                                      LocalDate toDate) {
        return !currentDate.isBefore(fromDate) && !currentDate.isAfter(toDate);
    }

    private int[] getEarnings(String[] names, String[] data,
                              LocalDate fromDate, LocalDate toDate) {
        int[] earnings = new int[names.length];
        for (String entry : data) {
            //parsing
            String[] parts = entry.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[0], formatter);
            String currentName = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);

            //calculate earnings
            if (isWithinDateRange(currentDate, fromDate, toDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (currentName.equals(names[i])) {
                        earnings[i] += hours * incomePerHour;
                        break;
                    }
                }
            }
        }
        return earnings;
    }
}
