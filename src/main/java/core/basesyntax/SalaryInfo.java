package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int datePosition = 0;
    private static final int namePosition = 1;
    public static final int hoursPosition = 2;
    public static final int incomePosition = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] recordData = record.split(" ");
                LocalDate recordDate = LocalDate.parse(recordData[datePosition], FORMATTER);
                if (recordData[namePosition].equals(name)
                        && (recordDate.isAfter(localDateFrom) || recordDate.isEqual(localDateFrom))
                        && (recordDate.isBefore(localDateTo) || recordDate.isEqual(localDateTo))) {
                    salary += Integer.parseInt(recordData[hoursPosition]) * Integer.parseInt(recordData[incomePosition]);
                }
            }
            stringBuilder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
