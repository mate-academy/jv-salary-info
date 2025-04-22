package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        try {
            Date from = sdf.parse(dateFrom);
            Date to = sdf.parse(dateTo);

            for (String record : data) {
                String[] parts = record.split(" ");
                Date workDate = sdf.parse(parts[0]);
                String employee = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                if (salaryMap.containsKey(employee)) {
                    if (!workDate.before(from) && !workDate.after(to)) {
                        int total = hours * rate;
                        salaryMap.put(employee, salaryMap.get(employee) + total);
                    }
                }
            }

            for (String name : names) {
                int salary = salaryMap.get(name);
                report.append(name).append(" - ").append(salary).append(System.lineSeparator());
            }

        } catch (ParseException e) {
            return "Invalid date format!";
        }

        return report.toString().trim();
    }
}
