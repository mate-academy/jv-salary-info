package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SalaryInfo {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private HashMap<String, Integer> map = new HashMap<>();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMAT);
        LocalDate to = LocalDate.parse(dateTo, FORMAT);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo + System.lineSeparator());
        for (String s : names) {
            map.put(s, 0);
        }
        for (String s : data) {
            String[] arr = s.split(" ");
            LocalDate date = LocalDate.parse(arr[0], FORMAT);
            String name = arr[1];
            int hours = Integer.parseInt(arr[2]);
            int salaryByHour = Integer.parseInt(arr[3]);
            if ((date.isAfter(from) || date.equals(from))
                    && (date.equals(to) || date.isBefore(to))) {
                map.put(name, map.get(name) + (salaryByHour * hours));
            }
        }
        for (String name : names) {
            result.append(name)
                    .append(" - ")
                    .append(map.get(name))
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
