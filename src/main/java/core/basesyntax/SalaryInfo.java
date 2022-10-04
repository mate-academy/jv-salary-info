package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int SALARY_MULTIPLIER_INDEX = 3;
    private static final String DATA_SEPARATOR = " ";
    private static final String LOG_INFO_DELIMITER = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder appender = new StringBuilder("Report for period ")
                .append(dateFrom).append(LOG_INFO_DELIMITER).append(dateTo);
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToParsed = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (String name : names) {
            int sum = 0;
            for (String row : data) {
                String[] info = row.split(DATA_SEPARATOR);
                if (!info[NAME_INDEX].equals(name)) {
                    continue;
                }
                LocalDate date = LocalDate.parse(info[DATE_INDEX], DATE_TIME_FORMATTER);
                boolean gte = date.isAfter(dateFromParsed) || date.isEqual(dateFromParsed);
                boolean lessThenOrEqual = date.isBefore(dateToParsed) || date.isEqual(dateToParsed);
                boolean dateBetween = gte && lessThenOrEqual;
                if (!dateBetween) {
                    continue;
                }
                sum += Integer.parseInt(info[SALARY_INDEX])
                        * Integer.parseInt(info[SALARY_MULTIPLIER_INDEX]);
            }
            appender
                    .append(System.lineSeparator())
                    .append(name)
                    .append(LOG_INFO_DELIMITER)
                    .append(sum);
        }
        return appender.toString();
    }
}
