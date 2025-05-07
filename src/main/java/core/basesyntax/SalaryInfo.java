package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter
            DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DELIMITER = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (String name : names) {
            int earnedMoney = 0;
            for (String entry : data) {
                String[] entryParts = entry.split(DELIMITER);
                LocalDate entryDate = LocalDate.parse(entryParts[DATE_INDEX], DATE_FORMATTER);
                if (entryParts[NAME_INDEX].equals(name) && !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)) {
                    int hours = Integer.parseInt(entryParts[HOURS_INDEX]);
                    int hourlyRate = Integer.parseInt(entryParts[HOURLY_RATE_INDEX]);
                    earnedMoney += hours * hourlyRate;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(earnedMoney);
        }

        return report.toString();
    }
}
