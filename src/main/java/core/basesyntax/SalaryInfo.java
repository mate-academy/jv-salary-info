package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] namesAndSalaries = new int[names.length];

        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String entry : data) {
            String[] parts = entry.split(" ");
            String entryDate = parts[DATE_INDEX];
            String name = parts[NAME_INDEX];
            int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
            int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX ]);

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
