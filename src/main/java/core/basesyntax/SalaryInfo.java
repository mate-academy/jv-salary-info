package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final String SPLIT_CHARACTER = " ";
    private static final int CURRENT_DATE_PARSE_NUMBER = 0;
    private static final int CURRENT_NAME_PARSE_NUMBER = 1;
    private static final int HOURS_PARSE_NUMBER = 2;
    private static final int INCOME_PER_HOUR_PARSE_NuMBER = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        int[] earnings = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String entry : data) {
            String[] parts = entry.split(SPLIT_CHARACTER);
            LocalDate currentDate = LocalDate.parse(parts[CURRENT_DATE_PARSE_NUMBER], formatter);
            String currentName = parts[CURRENT_NAME_PARSE_NUMBER];
            int hours = Integer.parseInt(parts[HOURS_PARSE_NUMBER]);
            int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_PARSE_NuMBER]);

            if (isWithinDateRange(currentDate, fromDate, toDate)) {
                int index = Arrays.asList(names).indexOf(currentName);
                if (index != -1) {
                    earnings[index] += hours * incomePerHour;
                }
            }
        }

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
}
