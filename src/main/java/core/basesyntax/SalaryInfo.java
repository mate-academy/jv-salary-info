package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int RECORD_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_STR_INDEX = 2;
    private static final int RATE_STR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = initializeReportBuilder(dateFrom, dateTo);
        generateReportContent(reportBuilder, names, data, dateFrom, dateTo);
        return reportBuilder.toString();
    }

    private StringBuilder initializeReportBuilder(String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        return reportBuilder;
    }

    private void generateReportContent(StringBuilder reportBuilder,
                                       String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int totalEarnings = 0;
            for (String record : data) {
                String[] parts = record.split(" ");
                if (parts.length != 4) {
                    continue;
                }
                LocalDate recordDate = LocalDate.parse(parts[RECORD_DATE_INDEX], formatter);
                String recordName = parts[NAME_INDEX];
                if (name.equals(recordName)) {
                    int hours = Integer.parseInt(parts[HOURS_STR_INDEX]);
                    int rate = Integer.parseInt(parts[RATE_STR_INDEX]);
                    if (isWithinDateRange(recordDate, fromDate, toDate)) {
                        totalEarnings += hours * rate;
                    }
                }
            }
            reportBuilder.append(name)
                    .append(" - ")
                    .append(totalEarnings);
            if (!name.equals(names[names.length - 1])) {
                reportBuilder.append(System.lineSeparator());
            }
        }
    }

    private boolean isWithinDateRange(LocalDate recordDate, LocalDate dateFrom, LocalDate dateTo) {
        return !recordDate.isBefore(dateFrom) && !recordDate.isAfter(dateTo);
    }
}
