package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String DASH = " - ";
    private static final int ENTRY_DATE_INDEX = 0;
    private static final int ENTRY_NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
            LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
            StringBuilder result = new StringBuilder("Report for period " + dateFrom + DASH
                    + dateTo + System.lineSeparator());

            for (String name : names) {
                int totalSalary = 0;

                for (String entry : data) {
                    String[] entryData = entry.split(SPACE);
                    String entryDateStr = entryData[ENTRY_DATE_INDEX];
                    String entryName = entryData[ENTRY_NAME_INDEX];
                    int hoursWorked = Integer.parseInt(entryData[HOURS_WORKED_INDEX]);
                    int hourlyRate = Integer.parseInt(entryData[HOURLY_RATE_INDEX]);
                    LocalDate entryDate = LocalDate.parse(entryDateStr, DATE_FORMATTER);

                    if (entryName.equals(name) && !entryDate.isBefore(fromDate)
                            && !entryDate.isAfter(toDate)) {
                        totalSalary += hoursWorked * hourlyRate;
                    }
                }
                result.append(name).append(DASH).append(totalSalary)
                        .append(System.lineSeparator());
            }
            return result.toString().trim();
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new RuntimeException("Error parsing dates or numbers.", e);
        }
    }
}
