package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        Map<String, Integer> earnings = new HashMap<>();
        for (String entry : data) {
            String[] parts = entry.split(" ");
            String dateString = parts[DATE_INDEX];
            String name = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int incomePerHour = Integer.parseInt(parts[INCOME_INDEX]);
            LocalDate entryDate = LocalDate.parse(dateString, DATE_FORMATTER);
            if (entryDate.compareTo(fromDate) >= 0 && entryDate.compareTo(toDate) <= 0) {
                int totalEarnings = earnings.getOrDefault(name, 0);
                totalEarnings += hours * incomePerHour;
                earnings.put(name, totalEarnings);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalEarnings = earnings.getOrDefault(name, 0);
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalEarnings);
        }
        return result.toString();
    }
}
