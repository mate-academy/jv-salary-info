package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int MONEY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int money = 0;
            for (String datum : data) {
                String[] splitData = datum.split(" ");
                LocalDate date = LocalDate.parse(splitData[DATE_INDEX], formatter);
                LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
                LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
                if (name.equals(splitData[NAME_INDEX])
                        && date.isAfter(localDateFrom.minusDays(1))
                        && date.isBefore(localDateTo.plusDays(1))) {
                    money += Integer.parseInt(splitData[HOUR_INDEX])
                            * Integer.parseInt(splitData[MONEY_INDEX]);
                }
            }
            result
                    .append(name)
                    .append(" - ")
                    .append(money)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
