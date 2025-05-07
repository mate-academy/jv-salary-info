package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_FORMAT_PATTERN);
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOUR_POSITION = 2;
    private static final int INCOME_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = getDate(dateFrom);
        LocalDate toDate = getDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataRow : data) {
                String[] rowElements = dataRow.split(" ");
                LocalDate date = getDate(rowElements[DATE_POSITION]);
                if (name.equals(rowElements[NAME_POSITION])
                        && (date.isEqual(fromDate) || date.isAfter(fromDate))
                        && (date.isEqual(toDate) || date.isBefore(toDate))) {
                    salary += Integer.parseInt(rowElements[HOUR_POSITION])
                            * Integer.parseInt(rowElements[INCOME_POSITION]);
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return result.toString();
    }

    private LocalDate getDate(String dateString) {
        return LocalDate.parse(dateString, DATE_TIME_FORMATTER);
    }
}
