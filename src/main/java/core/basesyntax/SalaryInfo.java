package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int INCOME_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        int money = 0;

        for (String name : names) {
            for (String line : data) {
                String[] record = line.split(" ");
                LocalDate recordDate = LocalDate.parse(record[DATE_POSITION], FORMATTER);
                if (name.equals(record[NAME_POSITION]) && (recordDate.isAfter(localFrom)
                        || recordDate.equals(localFrom)) && (recordDate.isBefore(localTo)
                        || recordDate.equals(localTo))) {
                    money += Integer.parseInt(record[HOURS_POSITION])
                            * Integer.parseInt(record[INCOME_POSITION]);
                }
            }
            builder.append("\n").append(name).append(" - ").append(money);
            money = 0;
        }
        return builder.toString();
    }
}
