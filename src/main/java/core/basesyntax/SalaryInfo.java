package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalIncomesByName = new int[names.length];

        for (String datum : data) {
            String[] words = datum.split(" ");

            String date = words[0];
            String name = words[1];

            if (isDateBetween(date, dateFrom, dateTo)) {
                for (int i = 0; i < names.length; i++) {
                    if (name.equals(names[i])) {
                        int hours = Integer.parseInt(words[2]);
                        int tax = Integer.parseInt(words[3]);
                        totalIncomesByName[i] += hours * tax;
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom + " - " + dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(totalIncomesByName[i]);
            if (i < names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    private boolean isDateBetween(String date, String dateFrom, String dateTo) {
        LocalDate target = LocalDate.parse(date, formatter);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        return (target.isAfter(from) || target.isEqual(from))
                && (target.isBefore(to) || target.isEqual(to));
    }
}
