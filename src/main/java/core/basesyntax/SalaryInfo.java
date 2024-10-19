package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> map = new LinkedHashMap<>();
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int money = 0;
            for (String record : data) {
                String[] arr = record.split(" ");
                String n = arr[1];
                if (name.equals(n)) {
                    LocalDate date = LocalDate.parse(arr[0], formatter);
                    if (dateIsValid(date, from, to)) {
                        money += Integer.parseInt(arr[3]) * Integer.parseInt(arr[2]);
                    }
                }
            }
            map.put(name, money);
        }
        return printResult(dateFrom, dateTo, map);
    }

    private boolean dateIsValid(LocalDate givenDate, LocalDate from, LocalDate to) {
        return givenDate.isAfter(from.minusDays(1)) && givenDate.isBefore(to.plusDays(1));
    }

    private String printResult(String from, String to, Map<String, Integer> records) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(from).append(" - ").append(to);
        for (String record : records.keySet()) {
            builder.append(System.lineSeparator())
                    .append(record)
                    .append(" - ")
                    .append(records.get(record));
        }
        return builder.toString();
    }
}
