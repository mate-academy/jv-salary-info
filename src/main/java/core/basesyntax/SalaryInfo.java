package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] results = new String[names.length];
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        int counter = 0;
        for (String name : names) {
            int money = 0;
            for (String record : data) {
                String[] arr = record.split(" ");
                String n = arr[1];
                if (name.equals(n)) {
                    LocalDate date = LocalDate.parse(arr[0], FORMATTER);
                    if (dateIsValid(date, from, to)) {
                        money += Integer.parseInt(arr[3]) * Integer.parseInt(arr[2]);
                    }
                }
            }
            results[counter] = name + " - " + money;
            counter++;
        }
        return printResult(dateFrom, dateTo, results);
    }

    private boolean dateIsValid(LocalDate givenDate, LocalDate from, LocalDate to) {
        return givenDate.isAfter(from.minusDays(1)) && givenDate.isBefore(to.plusDays(1));
    }

    private String printResult(String from, String to, String[] results) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(from).append(" - ").append(to);
        for (String record : results) {
            builder.append(System.lineSeparator())
                    .append(record);
        }
        return builder.toString();
    }
}
