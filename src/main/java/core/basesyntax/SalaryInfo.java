package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMATTER = "dd.MM.yyyy";
    private static final String DATA_LINE_SEPARATOR = " ";
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String datum : data) {
                String[] lineFromData = datum.split(DATA_LINE_SEPARATOR);
                LocalDate currentDate = LocalDate
                        .parse(lineFromData[INDEX_OF_DATE], dateTimeFormatter);
                if (name.equals(lineFromData[INDEX_OF_NAME]) && !currentDate.isAfter(localDateTo)
                        && !currentDate.isBefore(localDateFrom)) {
                    sum += Integer.parseInt(lineFromData[INDEX_OF_WORKING_HOURS])
                            * Integer.parseInt(lineFromData[INDEX_OF_INCOME_PER_HOUR]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return result.toString();
    }
}
