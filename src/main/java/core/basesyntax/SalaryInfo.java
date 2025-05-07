package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORK_HOURS = 2;
    private static final int INDEX_OF_RATE_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String dataItem : data) {
                String[] parts = dataItem.split(" ");
                try {
                    LocalDate currentDate = LocalDate.parse(parts[INDEX_OF_DATE], DATE_FORMATTER);
                    if (parts[INDEX_OF_NAME].equals(name) && (currentDate.isEqual(fromDate)
                            || currentDate.isEqual(toDate) || (currentDate.isAfter(fromDate)
                               && currentDate.isBefore(toDate)))) {
                        salary += (Integer.parseInt(parts[INDEX_OF_WORK_HOURS])
                             * Integer.parseInt(parts[INDEX_OF_RATE_PER_HOUR]));
                    }
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Invalid date format");
                }
            }
            result.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
