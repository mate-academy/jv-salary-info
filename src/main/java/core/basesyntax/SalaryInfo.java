package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        // Array to store salaries corresponding to names array
        int[] salaries = new int[names.length];

        // First loop: Process all data and update salaries array
        for (String line : data) {
            String[] parts = line.split("\\s+");
            LocalDate recordDate = LocalDate.parse(parts[0], FORMATTER);

            if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
                String name = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int hourlyRate = Integer.parseInt(parts[3]);

                // Find the name index in names[] and update salaries in the same loop
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * hourlyRate;
                        break; // Stop searching once a match is found
                    }
                }
            }
        }

        // Second loop: Build the result string
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String lineSeparator = System.lineSeparator();

        for (int i = 0; i < names.length; i++) {
            result.append(lineSeparator).append(names[i]).append(" - ").append(salaries[i]);
        }

        return result.toString();
    }
}
