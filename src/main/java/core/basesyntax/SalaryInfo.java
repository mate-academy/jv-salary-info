package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();

        try {
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
                    String entryDateStr = entryParts[0];
                    String entryName = entryParts[1];

                    try {
                        int hours = Integer.parseInt(entryParts[2]);
                        int hourlyRate = Integer.parseInt(entryParts[3]);
                        LocalDate entryDate = LocalDate.parse(entryDateStr, DATE_FORMATTER);

                        if (entryName.equals(name) && !entryDate.isBefore(fromDate)
                                && !entryDate.isAfter(toDate)) {
                            totalSalary += hours * hourlyRate;
                        }
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Invalid number format");
                    }
                }

                result.append(name)
                        .append(" - ")
                        .append(totalSalary);
                if (!name.equals(names[names.length - 1])) {
                    result.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while calculating salaries");
        }

        return result.toString();
    }
}
