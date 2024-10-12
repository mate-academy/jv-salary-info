package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                LocalDate day = parseDateFromString(parseRecord[0]);
                String name = parseRecord[1];
                int hours = parseNumberFromString(parseRecord[2]);
                int daySalary = parseNumberFromString(parseRecord[3]);

                if ((day.equals(localDateFrom) || day.isAfter(localDateFrom))
                        && (day.equals(localDateTo) || day.isBefore(localDateTo))
                        && currentName.equals(name)) {
                    salaryAmount += hours * daySalary;
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
            return LocalDate.parse(parseValue, formatter);
        } catch (Exception e) {
            throw new RuntimeException("We cann't parse date from the string:" + parseValue);
        }
    }

    private int parseNumberFromString(String parseValue) {
        try {
            return Integer.parseInt(parseValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException("We cann't parse number from the string:" + parseValue);
        }
    }
}
