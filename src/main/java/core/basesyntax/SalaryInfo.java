package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final String lineSeparator = System.lineSeparator();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        Map<String, Integer> salaryMap = new LinkedHashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }
        for (String dat : data) {
            String[] splitData = dat.split(" ");
            LocalDate date = LocalDate.parse(splitData[DATE_INDEX], dateFormatter);
            if (fromDate.isBefore(date) && toDate.isAfter(date)
                    || fromDate.equals(date) || toDate.equals(date)) {
                int hours = Integer.parseInt(splitData[HOURS_INDEX]);
                int perHour = Integer.parseInt(splitData[PER_HOUR_INDEX]);
                int salaryThatDate = hours * perHour;
                salaryMap.merge(splitData[NAME_INDEX], salaryThatDate, Integer::sum);
            }
        }
        return resultToString(salaryMap, dateFrom, dateTo);
    }

    public String resultToString(Map<String, Integer> result, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : result.keySet()) {
            stringBuilder.append(lineSeparator)
                    .append(name)
                    .append(" - ")
                    .append(result.get(name));
        }
        return stringBuilder.toString();
    }
}
