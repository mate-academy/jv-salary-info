package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        int[] salaries = new int[names.length];

        for (String line : data) {
            String[] parts = line.split("\\s+");
            LocalDate recordDate = LocalDate.parse(parts[0], FORMATTER);

            if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
                String name = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int hourlyRate = Integer.parseInt(parts[3]);

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * hourlyRate;
                        break;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append("\n   ").append(names[i]).append(" - ").append(salaries[i]);
        }

        return result.toString();
    }
}
