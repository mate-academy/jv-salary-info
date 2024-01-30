package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (dateFrom == null || dateTo == null || names.length == 0 || data.length == 0) {
            return "Invalid data. Dates should not be null,"
                    + "also names and data should not be empty";
        }

        try {
            LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
            int[] earnings = new int[names.length];
            for (String dataUnit : data) {
                String[] parts = dataUnit.split(" ");
                if (parts.length == 4) {
                    try {
                        LocalDate recordDate = LocalDate.parse(parts[0], FORMATTER);
                        if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                            String name = parts[1];
                            int hours = Integer.parseInt(parts[2]);
                            int perHour = Integer.parseInt(parts[3]);

                            for (int i = 0; i < names.length; i++) {
                                if (name.equals(names[i])) {
                                    earnings[i] += hours * perHour;
                                    break;
                                }
                            }
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Skipping invalid date: " + parts[0]);
                    }
                }
            }

            StringBuilder result = new StringBuilder("Report for period ")
                    .append(dateFrom).append(" - ").append(dateTo)
                    .append(System.lineSeparator());

            for (int i = 0; i < names.length; i++) {
                result.append(names[i]).append(" - ").append(earnings[i]);
                if (i < names.length - 1) {
                    result.append(System.lineSeparator());
                }
            }
            return result.toString();
        } catch (DateTimeParseException e) {
            return "Invalid date format. Correct format: dd.MM.yyyy";
        }
    }
}
