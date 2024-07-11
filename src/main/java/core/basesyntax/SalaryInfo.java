package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate;
        Date toDate;
        try {
            fromDate = dateFormat.parse(dateFrom);
            toDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Incorrect date format", e);
        }

        Map<String, Integer> salaries = new HashMap<>();
        for (String name : names) {
            salaries.put(name, 0);
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            try {
                Date entryDate = dateFormat.parse(parts[0]);
                if (!entryDate.before(fromDate) && !entryDate.after(toDate)) {
                    String employee = parts[1];
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int hourlyRate = Integer.parseInt(parts[3]);
                    if (salaries.containsKey(employee)) {
                        int currentSalary = salaries.get(employee);
                        salaries.put(employee, currentSalary + (hoursWorked * hourlyRate));
                    }
                }
            } catch (ParseException e) {
                throw new RuntimeException("Incorrect date format in data", e);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            report.append(System.lineSeparator())
                    .append(name).append(" - ").append(salaries.get(name));
        }

        return report.toString();
    }
}
