package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date startDate;
        Date endDate;
        Date entryDate;
        try {
            startDate = dateFormat.parse(dateFrom);
            endDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + dateFrom);
        }

        Map<String, Integer> salaries = new HashMap<>();
        for (String name : names) {
            salaries.put(name, 0);
        }

        for (String entry : data) {
            String[] entryParts = entry.split(" ");
            String dateStr = entryParts[0];
            String employeeName = entryParts[1];
            int hoursWorked = Integer.parseInt(entryParts[2]);
            int incomePerHour = Integer.parseInt(entryParts[3]);

            try {
                entryDate = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing entry date: " + dateStr);
            }

            if (!entryDate.before(startDate)
                    && !entryDate.after(endDate)
                    && salaries.containsKey(employeeName)) {
                int currentSalary = salaries.get(employeeName);
                currentSalary += hoursWorked * incomePerHour;
                salaries.put(employeeName, currentSalary);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\r\n");

        for (String name : names) {
            result.append(name)
                    .append(" - ")
                    .append(salaries.get(name))
                    .append("\r\n");
        }

        return result.toString().trim();
    }
}
