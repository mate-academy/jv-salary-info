package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate;
        LocalDate toDate;

        try {
            fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
            toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return "Invalid date format";
        }

        Map<String, Integer> earnings = new HashMap<>();

        final int index_date_part = 0;
        final int index_name_part = 1;
        final int index_hours_worked_part = 2;
        final int index_income_per_hour_part = 3;

        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split(" ");
            String currentDate = parts[index_date_part];
            String currentName = parts[index_name_part];
            int hoursWorked = Integer.parseInt(parts[index_hours_worked_part]);
            int incomePerHour = Integer.parseInt(parts[index_income_per_hour_part]);

            try {
                LocalDate currentDateParsed = LocalDate.parse(currentDate, DATE_FORMATTER);
                if (currentDateParsed.compareTo(fromDate) >= 0 && currentDateParsed
                        .compareTo(toDate) <= 0) {
                    earnings.put(currentName, earnings.getOrDefault(currentName,
                            0) + (hoursWorked * incomePerHour));
                }
            } catch (DateTimeParseException e) {
                return "Invalid date format in data";
            }
        }

        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());

        for (String name : names) {
            int earned = earnings.getOrDefault(name, 0);
            result.append(name).append(" - ").append(earned).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
