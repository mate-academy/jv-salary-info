package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String REPORT_TITLE = "Report for period";
    public static final String INFORMATION_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salariesResults = new int[names.length];

        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (int i = 0; i < names.length; i += 1) {
            for (String row : data) {
                String[] rowData = row.split(" ");

                LocalDate parsedDate = LocalDate.parse(rowData[0], DATE_TIME_FORMATTER);

                String name = rowData[1];
                int hours = Integer.parseInt(rowData[2]);
                int salary = Integer.parseInt(rowData[3]);

                if (this.isDateInRange(startDate, endDate, parsedDate) && names[i].equals(name)) {
                    salariesResults[i] += hours * salary;
                }
            }
        }

        return this.generateReport(names, dateFrom, dateTo, salariesResults);
    }

    private boolean isDateInRange (LocalDate minDate, LocalDate maxDate, LocalDate targetDate) {
        return targetDate.isAfter(minDate)
                && (targetDate.isBefore(maxDate)
                || targetDate.equals(maxDate));
    }

    private String generateReport(
            String[] names,
            String dateFrom,
            String dateTo,
            int[] salariesResults
    ) {
        StringBuilder stringBuilder = new StringBuilder(REPORT_TITLE + " ")
                .append(dateFrom)
                .append(INFORMATION_SEPARATOR)
                .append(dateTo);

        for (int i = 0; i < names.length; i += 1) {
            stringBuilder
                    .append(System.lineSeparator())
                    .append(names[i])
                    .append(INFORMATION_SEPARATOR)
                    .append(salariesResults[i]);
        }

        return stringBuilder.toString();
    }
}
