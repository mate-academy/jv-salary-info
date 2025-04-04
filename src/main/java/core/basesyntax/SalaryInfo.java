package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalaryInfo {
    public static String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) throws ParseException {
        // Date format used in the input
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        // Parse the dateFrom and dateTo into Date objects
        Date from = dateFormat.parse(dateFrom);
        Date to = dateFormat.parse(dateTo);

        // Map to store salaries by employee name
        Map<String, Double> salaryMap = new HashMap<>();

        // Iterate through the data array
        for (String entry : data) {
            // Split the entry to get the date, name, hours worked, and income per hour
            String[] parts = entry.split(" ");
            Date entryDate = dateFormat.parse(parts[0]);
            String name = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            double incomePerHour = Double.parseDouble(parts[3]);

            // Check if the date is within the specified range
            if ((entryDate.equals(from) || entryDate.after(from)) && (entryDate.equals(to) || entryDate.before(to))) {
                // Calculate the salary for this entry
                double earnings = hoursWorked * incomePerHour;
                // Add the earnings to the employee's total salary
                salaryMap.put(name, salaryMap.getOrDefault(name, 0.0) + earnings);
            }
        }

        // Build the result string
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        // Add each employee's salary to the result
        for (String name : names) {
            double totalSalary = salaryMap.getOrDefault(name, 0.0);
            result.append(name).append(" - ").append((int) totalSalary).append("\n");
        }

        return result.toString();
    }
}
