package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate localDateFrom = parseDateFromString(dateFrom);
        LocalDate localDateTo = parseDateFromString(dateTo);

        report.append("Report for period ")
              .append(dateFrom)
              .append(" - ")
              .append(dateTo);

        for (String currentName : names) {
            int salaryAmount = 0;
            for (String record : data) {
                if (record == null || record.isEmpty()) {
                    continue;
                }
                String[] parseRecord = record.split(" ");
                LocalDate workDate = parseDateFromString(parseRecord[0]);
                String name = parseRecord[1];
                int hoursWorked = parseNumberFromString(parseRecord[2]);
                int hourlyRate = parseNumberFromString(parseRecord[3]);

                if ((workDate.isEqual(localDateFrom) || workDate.isAfter(localDateFrom))
                        && (workDate.isEqual(localDateTo) || workDate.isBefore(localDateTo))
                        && currentName.equals(name)) {
                    salaryAmount += hoursWorked * hourlyRate;
                }
            }
            report.append(System.lineSeparator())
                  .append(currentName)
                  .append(" - ")
                  .append(salaryAmount);
        }

        return report.toString();
    }

    private LocalDate parseDateFromString(String parseValue) {
        try {
            return LocalDate.parse(parseValue, DATE_FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse date from the string: " + parseValue, e);
        }
    }

    private int parseNumberFromString(String parseValue) {
        try {
            return Integer.parseInt(parseValue);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse number from the string: " + parseValue, e);
        }
    }
}
