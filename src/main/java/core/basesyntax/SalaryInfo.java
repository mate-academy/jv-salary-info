package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    private static final int ENTRY_DATE_INDEX = 0;
    private static final int ENTRY_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();

        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;

            for (String entry : data) {
                String[] entryParts = entry.split(" ");
                String entryDateStr = entryParts[ENTRY_DATE_INDEX];
                String entryName = entryParts[ENTRY_NAME_INDEX];

                int hours = Integer.parseInt(entryParts[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(entryParts[HOURLY_RATE_INDEX]);
                LocalDate entryDate = LocalDate.parse(entryDateStr, DATE_FORMATTER);

                if (entryName.equals(name) && !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)) {
                    totalSalary += hours * hourlyRate;
                }
            }

            result.append(name)
                    .append(" - ")
                    .append(totalSalary);
            if (!name.equals(names[names.length - 1])) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
