package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            Map<String, Integer> salaryMap = new HashMap<>();
            for (String name : names) {
                salaryMap.put(name, 0);
            }

            for (String entry : data) {
                String[] parts = entry.split(" ");
                String entryDateStr = parts[0];
                Date entryDate = dateFormat.parse(entryDateStr);

                if (!entryDate.before(fromDate) && !entryDate.after(toDate)) {
                    String name = parts[1];
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int incomePerHour = Integer.parseInt(parts[3]);
                    int currentSalary = salaryMap.get(name);
                    salaryMap.put(name, currentSalary + (hoursWorked * incomePerHour));
                }
            }

            StringBuilder report = new StringBuilder(String.format(
                    "Report for period %s - %s%s", dateFrom, dateTo, System.lineSeparator()));
            for (String name : names) {
                int salary = salaryMap.get(name);
                report.append(name.trim()).append(" - ")
                        .append(salary)
                        .append(System.lineSeparator());
            }
            return report.toString().trim();
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error occurred while parsing dates.";
        }
    }
}
