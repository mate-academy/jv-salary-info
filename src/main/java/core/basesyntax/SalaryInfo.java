package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_TEXT = "Report for period ";
    private static final String DELIMITER = " - ";
    private static final String SPACE_CHAR = " ";
    private static final int DATE_POS = 0;
    private static final int NAME_POS = 1;
    private static final int HOURS_POS = 2;
    private static final int PAY_RATE_POS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginDate = parseLocalDate(dateFrom);
        LocalDate endDate = parseLocalDate(dateTo);
        StringBuilder stringBuilder = new StringBuilder(REPORT_TEXT).append(dateFrom)
                .append(DELIMITER).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] splitData = record.split(SPACE_CHAR);
                LocalDate date = parseLocalDate(splitData[DATE_POS]);
                String currentName = splitData[NAME_POS];
                int workingHours = parseInteger(splitData[HOURS_POS]);
                int payRate = parseInteger(splitData[PAY_RATE_POS]);
                if (name.equals(currentName) && !date.isBefore(beginDate)
                        && !date.isAfter(endDate)) {
                    salary += payRate * workingHours;
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(DELIMITER)
                    .append(salary);
        }
        return stringBuilder.toString();
    }

    private LocalDate parseLocalDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse date: " + date);
        }
    }

    private int parseInteger(String stringToParse) {
        try {
            return Integer.parseInt(stringToParse);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse String: " + stringToParse);
        }
    }
}
