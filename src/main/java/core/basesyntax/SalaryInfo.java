package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LinkedHashMap<String, Integer> employees = new LinkedHashMap<>();
        for (String name : names) {
            employees.put(name, 0);
        }

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        for (String string : data) {
            String[] dataInfo = string.split(" ");
            LocalDate localDate = LocalDate.parse(dataInfo[0], FORMATTER);
            if (localDate.equals(from) || localDate.equals(to)
                    || localDate.isAfter(from) && localDate.isBefore(to)) {
                employees.put(dataInfo[1], employees.get(dataInfo[1])
                        + Integer.parseInt(dataInfo[2]) * Integer.parseInt(dataInfo[3]));
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (Map.Entry<String, Integer> entry : employees.entrySet()) {
            builder.append("\n").append(entry.getKey()).append(" - ").append(entry.getValue());
        }
        return builder.toString();
    }
}
