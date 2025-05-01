package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String SEPARATOR = " - ";
    private static final DateTimeFormatter
            DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(SEPARATOR).append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int sumOfMoney = 0;
            result.append(name).append(SEPARATOR);
            for (String currentData : data) {
                String[] parts = currentData.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_TIME_FORMATTER);
                String employeeName = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);

                if (!date.isBefore(startDate) && !date.isAfter(endDate)
                        && name.equals(employeeName)) {
                    sumOfMoney += hours * incomePerHour;
                }
            }
            result.append(sumOfMoney).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
