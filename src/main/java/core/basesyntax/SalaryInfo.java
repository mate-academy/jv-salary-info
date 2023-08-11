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
            for (String entry : data) {
                String[] parts = entry.split(" ");
                if (parts.length == 4) {
                    Date entryDate = dateFormat.parse(parts[0]);
                    if (!entryDate.before(fromDate) && !entryDate.after(toDate)) {
                        int earnedMoney = Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                        salaryMap.put(parts[1], salaryMap.getOrDefault(parts[1], 0) + earnedMoney);
                    }
                }
            }
            StringBuilder report = new StringBuilder();
            report.append("Report for period ")
                    .append(dateFrom).append(" - ")
                    .append(dateTo);
            for (String name : names) {
                int earnedMoney = salaryMap.getOrDefault(name, 0);
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
