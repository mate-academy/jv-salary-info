package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                    LocalDate entryDate = LocalDate.parse(entryParts[0], DATE_FORMATTER);
                    String entryName = entryParts[1];

                    try {
                        int hours = Integer.parseInt(entryParts[2]);
                        int hourlyRate = Integer.parseInt(entryParts[3]);

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
