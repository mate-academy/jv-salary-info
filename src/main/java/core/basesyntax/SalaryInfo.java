package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        dateFrom = dateFrom.trim();
        dateTo = dateTo.trim();

        StringBuilder report = new StringBuilder();
        appendReportHeader(report, dateFrom, dateTo);

        try {
            LocalDate fromDate = parseDate(dateFrom);
            LocalDate toDate = parseDate(dateTo);
            int isLastEntry = names.length - 1;
            for (String name : names) {
                int earnedMoney = calculateEarnedMoneyForName(name, data, fromDate, toDate);
                appendReportEntry(report, name, earnedMoney);
            }
        } catch (DateTimeParseException exception) {
            throw new RuntimeException("Can't parse this date", exception);
        }

        return report.toString();
    }

    private void appendReportHeader(StringBuilder report, String dateFrom, String dateTo) {
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
    }

    private LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    private int calculateEarnedMoneyForName(
            String name, String[] data, LocalDate fromDate, LocalDate toDate) {
        int earnedMoney = 0;
        for (String entry : data) {
            String[] entryData = entry.split(" ");
            LocalDate checkDates = parseDate(entryData[DATE_INDEX]);
            if (isWithinDateRange(checkDates, fromDate, toDate)
                    && isMatchingName(name, entryData[NAME_INDEX])) {
                int hours = Integer.parseInt(entryData[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(entryData[HOURLY_RATE_INDEX]);
                earnedMoney = earnedMoney + (hours * hourlyRate);
            }
        }
        return earnedMoney;
    }

    private boolean isWithinDateRange(LocalDate entryDate, LocalDate fromDate, LocalDate toDate) {
        return !entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate);
    }

    private boolean isMatchingName(String name, String workerName) {
        return workerName.equals(name);
    }

    private void appendReportEntry(StringBuilder report, String name, int earnedMoney) {
        if (report.length() > 0) {
            report.append(System.lineSeparator());
        }
        report.append(name).append(" - ").append(earnedMoney);
    }
}


