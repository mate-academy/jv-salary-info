package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate formattedlocalDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int total = 0;
            for (String line : data) {
                String[] arrayLine = line.split(" ");
                LocalDate lineDate = LocalDate.parse(arrayLine[INDEX_OF_DATE], FORMATTER);
                if (lineDate.isAfter(formattedDateFrom.minusDays(1))
                        && lineDate.isBefore(formattedlocalDateTo.plusDays(1))
                        && arrayLine[INDEX_OF_NAME].equals(name)) {
                    int hours = Integer.parseInt(arrayLine[INDEX_OF_HOURS]);
                    int salary = Integer.parseInt(arrayLine[INDEX_OF_SALARY]);
                    total += hours * salary;
                }
            }
            stringBuilder.append(name).append(" - ").append(total).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
