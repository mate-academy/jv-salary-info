package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String ENTRY_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int sumUserSalary = 0;
            for (String entry : data) {
                try {
                    String[] parts = entry.split(ENTRY_SEPARATOR);
                    LocalDate date = LocalDate.parse(parts[DATE_INDEX], TIME_FORMATTER);
                    String nameUser = parts[NAME_INDEX];
                    int workHours = Integer.parseInt(parts[HOURS_INDEX]);
                    int perHourRate = Integer.parseInt(parts[RATE_INDEX]);
                    if (name.equals(nameUser) && !date.isAfter(endDate) && !date.isBefore(startDate)) {
                        sumUserSalary += workHours * perHourRate;
                    }

                } catch (Exception e) {
                    System.err.println("Invalid data entry: " + entry);
                }

            }
            result.append(name)
                    .append(" - ")
                    .append(sumUserSalary)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date.trim(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd.MM.yyyy");
        }
    }
}
