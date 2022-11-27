package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int INDEX_OF_INCOME = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] info = line.split(" ");
                LocalDate date = LocalDate.parse(info[INDEX_OF_DATE], FORMATTER);
                if (date.compareTo(localDateFrom) >= 0
                        && date.compareTo(localDateTo) <= 0
                        && name.equals(info[INDEX_OF_NAME])) {
                    int hour = Integer.parseInt(info[INDEX_OF_HOUR]);
                    int income = Integer.parseInt(info[INDEX_OF_INCOME]);
                    salary += hour * income;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
