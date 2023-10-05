package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                LocalDate recordDate = LocalDate.parse(recordData[0], FORMATTER);
                if (recordData[1].equals(name)
                        && (recordDate.isAfter(localDateFrom) || recordDate.isEqual(localDateFrom))
                        && (recordDate.isBefore(localDateTo) || recordDate.isEqual(localDateTo))) {
                    salary += Integer.parseInt(recordData[2]) * Integer.parseInt(recordData[3]);
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
