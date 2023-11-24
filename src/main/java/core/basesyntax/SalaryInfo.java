package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int POSITION_DATE = 0;
    private static final int POSITION_NAME = 1;
    private static final int POSITION_HOURS_WORKED = 2;
    private static final int POSITION_HOURLY_INCOME = 3;
    private static final int DATA_ELEMENTS_COUNT = 4;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFromString,
                                String dateToString) {
        LocalDate dateFrom = LocalDate.parse(dateFromString, formatter);
        LocalDate dateTo = LocalDate.parse(dateToString, formatter);
        if (dateFrom.isAfter(dateTo)) {
            throw new DateTimeException("Invalid period");
        }
        StringBuilder result = new StringBuilder("Report for period ").append(dateFromString)
                .append(" - ").append(dateToString);
        for (String name: names) {
            int salary = 0;
            for (String record: data) {
                String[] parts = record.split(" ");
                if (parts.length == DATA_ELEMENTS_COUNT && name.equals(parts[POSITION_NAME])) {
                    LocalDate recordDate = LocalDate.parse(parts[POSITION_DATE], formatter);
                    if (!dateFrom.isAfter(recordDate) && !dateTo.isBefore(recordDate)) {
                        salary += Integer.parseInt(parts[POSITION_HOURS_WORKED])
                                * Integer.parseInt(parts[POSITION_HOURLY_INCOME]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
