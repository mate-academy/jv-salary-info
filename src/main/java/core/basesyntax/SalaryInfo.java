package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOUR = 2;
    private static final int MONEY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
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
                LocalDate date = LocalDate.parse(splitData[DATE], FORMATTER);
                LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
                if (name.equals(splitData[NAME]) && (date.equals(localDateTo)
                        || date.equals(localDateFrom) || date.isAfter(localDateFrom)
                        && date.isBefore(localDateTo))) {
                    money += Integer.parseInt(splitData[HOUR])
                            * Integer.parseInt(splitData[MONEY_PER_HOUR]);
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(money)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
