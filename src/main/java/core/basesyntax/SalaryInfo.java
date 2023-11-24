package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private static final String DATA_PARTS_SEPARATOR = " ";
    private static final String REPORT_DATE_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(REPORT_DATE_SEPARATOR)
                .append(dateTo).append(System.lineSeparator());
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (String nameOfPeople : names) {
            int moneyEarned = 0;
            for (String dataFromArray : data) {
                String[] parts = dataFromArray.split(DATA_PARTS_SEPARATOR);
                String name = parts[NAME_INDEX];
                if (name.equals(nameOfPeople)) {
                    String date = parts[DATE_INDEX];
                    LocalDate localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
                    String hours = parts[HOURS_WORKED_INDEX];
                    String incomePerHour = parts[HOURLY_RATE_INDEX];
                    if (!localDate.isAfter(localDateTo) && !localDate.isBefore(localDateFrom)) {
                        moneyEarned += Integer.parseInt(hours) * Integer.parseInt(incomePerHour);
                    }
                }
            }
            resultBuilder.append(nameOfPeople).append(REPORT_DATE_SEPARATOR)
                    .append(moneyEarned).append(System.lineSeparator());
        }
        return resultBuilder.toString().trim();
    }
}
