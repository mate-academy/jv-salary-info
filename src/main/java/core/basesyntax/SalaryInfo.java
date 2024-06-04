package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate;
        Date toDate;

        try {
            fromDate = dateFormat.parse(dateFrom);
            toDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }

        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            Date entryDate;
            try {
                entryDate = dateFormat.parse(parts[0]);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format in data entry", e);
            }
            String name = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if (entryDate.compareTo(fromDate) >= 0 && entryDate.compareTo(toDate) <= 0
                    && salaryMap.containsKey(name)) {
                int currentSalary = salaryMap.get(name);
                salaryMap.put(name, currentSalary + (hoursWorked * hourlyRate));
            }
        }

        StringJoiner report = new StringJoiner(System.lineSeparator());
        report.add("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            report.add(name + " - " + salaryMap.get(name));
        }
        return report.toString().trim();
    }
}
