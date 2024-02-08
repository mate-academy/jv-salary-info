package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int perHour = 0;
        String[] splitedString = new String[4];
        LocalDate parseSplitedDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);
        Map<String, Integer> totalHoursMap = new LinkedHashMap<>();
        for (String d : data) {
            splitedString = d.split(" ");
            parseSplitedDate = LocalDate.parse(splitedString[0], formatter);
            if ((parseSplitedDate.isAfter(parseDateFrom)
                    || parseSplitedDate.isEqual(parseDateFrom))
                    && (parseSplitedDate.isBefore(parseDateTo)
                    || parseSplitedDate.isEqual(parseDateTo))) {
                for (String name : names) {
                    if (name.equals(splitedString[1])) {
                        perHour = Integer.parseInt(splitedString[2])
                                * Integer.parseInt(splitedString[3]);
                        totalHoursMap.put(name, totalHoursMap
                                .getOrDefault(name, 0) + perHour);
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (Map.Entry<String, Integer> entry : totalHoursMap.entrySet()) {
            stringBuilder.append("\n")
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue());
        }
        return stringBuilder.toString();
    }
}
