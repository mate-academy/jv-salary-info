package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = DATE_FORMATTER.parse(dateFrom);
            toDate = DATE_FORMATTER.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format");
        }
        Map<String, Integer> employeeSalaries = new HashMap<>();
        for (String entry : data) {
            String[] parts = entry.split(" ");
            if (parts.length != 4) {
                throw new RuntimeException("Invalid data format");
            }
            String entryDateStr = parts[0];
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);
            try {
                Date entryDate = DATE_FORMATTER.parse(entryDateStr);
                if (entryDate.compareTo(fromDate) >= 0 && entryDate.compareTo(toDate) <= 0) {
                    int salary = hoursWorked * incomePerHour;
                    employeeSalaries.put(employeeName, employeeSalaries.getOrDefault(employeeName, 0) + salary);
                }
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format");
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = employeeSalaries.getOrDefault(name, 0);
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString();
    }
}
