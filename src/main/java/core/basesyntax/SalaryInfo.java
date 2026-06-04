package core.basesyntax;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> dataMap = new LinkedHashMap<>();

        for (String name : names) {
            dataMap.put(name, 0);
        }

        LocalDate from = formatDate(dateFrom);
        LocalDate to = formatDate(dateTo);

        for (String record : data) {
            String[] fields = record.split(" ");

            String name = fields[1];
            LocalDate currentDate = formatDate(fields[0]);

            if (!currentDate.isBefore(from)
                    && !currentDate.isAfter(to)
                    && dataMap.containsKey(name)) {
                int hours = Integer.parseInt(fields[2]);
                int rate = Integer.parseInt(fields[3]);

                dataMap.put(name, dataMap.get(name) + hours * rate);
            }
        }

        return createReport(dataMap, dateFrom, dateTo);
    }

    private LocalDate formatDate(String date) {
        String[] parts = date.split("\\.");

        return LocalDate.of(
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[0])
        );
    }

    private String createReport(Map<String, Integer> map, String start, String end) {
        StringBuilder sb = new StringBuilder("Report for period " + start + " - " + end);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(System.lineSeparator())
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue());
        }

        return sb.toString();
    }
}
