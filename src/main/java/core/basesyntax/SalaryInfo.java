package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private static final String DASH_SEPARATOR = " - ";
    private static final String NEW_LINE = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(DASH_SEPARATOR)
                .append(dateTo)
                .append(NEW_LINE);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                totalSalary += calculateSalaryForNameAndDate(name,
                        record, dateFrom, dateTo);
            }
            result.append(name)
                    .append(DASH_SEPARATOR)
                    .append(totalSalary)
                    .append(NEW_LINE);
        }

        return result.toString().trim();
    }

    private int calculateSalaryForNameAndDate(String name, String record,
                                              String dateFrom, String dateTo) {
        String[] recordParts = record.split(" ");
        String recordDate = recordParts[DATE_INDEX];

        if (isDateInRange(recordDate, dateFrom, dateTo) && record.contains(name)) {
            int hoursWorked = Integer.parseInt(recordParts[HOURS_WORKED_INDEX]);
            int hourlyRate = Integer.parseInt(recordParts[HOURLY_RATE_INDEX]);
            return hoursWorked * hourlyRate;
        }
        return 0;
    }

    private boolean isDateInRange(String recordDate, String dateFrom, String dateTo) {
        LocalDate recordLocalDate = LocalDate.parse(recordDate, DATE_TIME_FORMATTER);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        return !recordLocalDate.isBefore(fromDate) && !recordLocalDate.isAfter(toDate);
    }
}
