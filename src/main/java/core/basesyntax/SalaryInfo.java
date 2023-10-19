package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] namesAndSalaries = new int[names.length];

        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String entry : data) {
            String[] parts = entry.split(" ");
            String entryDate = parts[0];
            String name = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            LocalDate entryDateFormatted = LocalDate.parse(entryDate, DATE_FORMAT);

            if (!entryDateFormatted.isBefore(dateFromFormatted)
                    && !entryDateFormatted.isAfter(dateToFormatted)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        namesAndSalaries[i] += hoursWorked * hourlyRate;
                        break;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(namesAndSalaries[i]);
        }

        return result.toString();
    }
}
