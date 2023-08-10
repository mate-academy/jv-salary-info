package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SalaryInfo {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);
            HashMap<String, Integer> salaryMap = new HashMap<>();
            for (String name : names) {
                salaryMap.put(name, 0);
            }
            for (String entry : data) {
                String[] parts = entry.split(" ");
                if (parts.length == 4) {
                    String entryDateStr = parts[0];
                    String name = parts[1];
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int incomePerHour = Integer.parseInt(parts[3]);
                    Date entryDate = dateFormat.parse(entryDateStr);
                    if (!entryDate.before(fromDate) && !entryDate.after(toDate)) {
                        int earnedMoney = hoursWorked * incomePerHour;
                        int currentSalary = salaryMap.get(name);
                        salaryMap.put(name, currentSalary + earnedMoney);
                    }
                }
            }
            StringBuilder report = new StringBuilder();
            report.append("Report for period ")
                    .append(dateFrom).append(" - ")
                    .append(dateTo);
            for (String name : names) {
                int earnedMoney = salaryMap.get(name);
                report.append(System.lineSeparator())
                        .append(name).append(" - ")
                        .append(earnedMoney);
            }
            return report.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while calculating salaries.";
        }
    }
}
