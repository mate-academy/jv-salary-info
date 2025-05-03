package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer > result = new HashMap<>();
        for (String value : names) {
            result.put(value, 0);
        }


        for (String datum : data) {
            String[] s = datum.split(" ");
            String date = s[0];
            String name = s[1];
            int jobTimes = Integer.parseInt(s[2]);
            int salaryTime = Integer.parseInt(s[3]);
            boolean betweenDates = isBetweenDates(dateFrom, dateTo, date);
            if (result.containsKey(name) && betweenDates) {
                int salary = (jobTimes * salaryTime) + result.get(name);
                result.put(name, salary);
            }
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String n : names) {
            stringBuilder.append("\n").append(n).append(" - ").append(result.get(n));
        }

        return stringBuilder.toString();
    }

    private boolean isBetweenDates( String dateFrom, String dateTo, String date ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate checkDate = LocalDate.parse(date, formatter);
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}
