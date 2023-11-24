package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int amount = 0;
            for (String strRecord : data) {
                String[] record = strRecord.split(" ");
                String date = record[DATE_INDEX];
                if (name.equals(record[NAME_INDEX]) && isBetween(date, dateFrom, dateTo)) {
                    int hours = Integer.parseInt(record[HOURS_INDEX]);
                    int rate = Integer.parseInt(record[RATE_INDEX]);
                    amount += hours * rate;
                }
            }
            result.append(System.lineSeparator()).append(name)
                    .append(" - ").append(amount);
        }
        return result.toString();
    }

    private boolean isBetween(String dateCurrent, String dateFrom, String dateTo) {
        LocalDate date = LocalDate.parse(dateCurrent, FORMATTER);
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        return !(date.isAfter(to) || date.isBefore(from));
    }
}
