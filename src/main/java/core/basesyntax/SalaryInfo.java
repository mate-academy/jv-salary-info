package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final char DATE_DELIMITER_IN_FILE = '.';
    public static final char DATE_DELIMITER_FOR_PARSE = '-';
    public static final String DATE_FORMAT_PATTERN = "dd-MM-yyyy";
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int HOUR_POSITION = 2;
    public static final int INCOME_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = getDate(dateFrom
                .replace(DATE_DELIMITER_IN_FILE, DATE_DELIMITER_FOR_PARSE));
        LocalDate toDate = getDate(dateTo
                .replace(DATE_DELIMITER_IN_FILE, DATE_DELIMITER_FOR_PARSE));
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataRow : data) {
                String[] rowElements = dataRow.split(" ");
                LocalDate date = getDate(rowElements[DATE_POSITION]
                        .replace(DATE_DELIMITER_IN_FILE, DATE_DELIMITER_FOR_PARSE));
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        return LocalDate.parse(dateString, dateTimeFormatter);
    }
}
