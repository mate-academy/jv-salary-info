package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> employeesSalaries = getEmployeesSalaries(data, dateFrom, dateTo);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        for (String name : names) {
            int totalSalary = employeesSalaries.getOrDefault(name,0);
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return result.toString();
    }

    private Map<String,Integer> getEmployeesSalaries(String[] data,
                                                     String dateFrom, String dateTo) {
        Map<String,Integer> localMap = new HashMap<>();
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom,SIMPLE_DATE_FORMAT);
            LocalDate toDate = LocalDate.parse(dateTo,SIMPLE_DATE_FORMAT);
            for (String entry : data) {
                String[] parts = entry.split(" ");
                String dateString = parts[0];
                String name = parts[1];
                int workingHours = Integer.parseInt(parts[2]);
                int incomePerHour = Integer.parseInt(parts[3]);
                LocalDate date = LocalDate.parse(dateString,SIMPLE_DATE_FORMAT);
                if (date.isAfter(fromDate) && date.isBefore(toDate)
                        || date.equals(fromDate) || date.equals(toDate)) {
                    int totalIncome = workingHours * incomePerHour;
                    localMap.put(name, localMap.getOrDefault(name,0)
                            + totalIncome);
                }
            }
        } catch (DateTimeParseException | NumberFormatException e) {
           e.printStackTrace();
        }
        return localMap;
    }
}
