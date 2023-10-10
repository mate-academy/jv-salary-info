package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SalaryInfo {
    static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        HashMap<String, Integer> earnings = new HashMap<>();
        for (String entry : data) {
            String[] parts = entry.split(" ");
            String dateString = parts[0];
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);
            LocalDate entryDate = LocalDate.parse(dateString, formatter);
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
