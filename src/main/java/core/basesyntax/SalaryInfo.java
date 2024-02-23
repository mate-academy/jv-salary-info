package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int RECORD_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_STR_INDEX = 2;
    private static final int RATE_STR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();

        initializeReportHeader(reportBuilder, dateFrom, dateTo);
        int[] earnings = calculateEarnings(names, data, dateFrom, dateTo);
        generateReportContent(reportBuilder, names, earnings);

        return reportBuilder.toString();
    }

    private void initializeReportHeader(StringBuilder reportBuilder,
                                        String dateFrom,
                                        String dateTo) {
        reportBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
    }

    private int[] calculateEarnings(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        int[] earnings = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            if (parts.length == 4) {
                LocalDate recordDate = LocalDate.parse(parts[RECORD_DATE_INDEX], formatter);
                String name = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_STR_INDEX]);
                int rate = Integer.parseInt(parts[RATE_STR_INDEX]);

                if (isWithinDateRange(recordDate, fromDate, toDate)) {
                    for (int i = 0; i < names.length; i++) {
                        if (name.equals(names[i])) {
                            earnings[i] += hours * rate;
                            break; // No need to continue searching for the name
                        }
                    }
                }
            }
        }
        return earnings;
    }

    private void generateReportContent(StringBuilder reportBuilder,
                                       String[] names,
                                       int[] earnings) {
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i])
                    .append(" - ")
                    .append(earnings[i]);
            if (i < names.length - 1) {
                reportBuilder.append(System.lineSeparator());
            }
        }
    }

    private boolean isWithinDateRange(LocalDate recordDate, LocalDate dateFrom, LocalDate dateTo) {
        return !recordDate.isBefore(dateFrom) && !recordDate.isAfter(dateTo);
    }
}
