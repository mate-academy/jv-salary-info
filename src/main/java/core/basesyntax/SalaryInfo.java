package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Set<String> employeeNames = new HashSet<>(Arrays.asList(names));

        Map<String, Integer> salaries = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date startDate;
        Date endDate;

        try {
            startDate = dateFormat.parse(dateFrom);
            endDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            return "Error: Invalid date format in dateFrom or dateTo.";
        }

        for (String record : data) {
            String[] parts = record.split(" ");

            if (parts.length < 4 || !employeeNames.contains(parts[1])) {
                continue;
            }

            String dateStr = parts[0];
            String name = parts[1];

            try {
                Date recordDate = dateFormat.parse(dateStr);

                if (recordDate.compareTo(startDate) >= 0 && recordDate.compareTo(endDate) <= 0) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    int earnings = hours * rate;

                    salaries.put(name, salaries.getOrDefault(name, 0) + earnings);
                }
            } catch (ParseException | NumberFormatException e) {
                continue;
            }
        }

        StringBuilder report = new StringBuilder();

        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(" \n");

        for (String name : names) {
            int totalSalary = salaries.getOrDefault(name, 0);
            report.append(name).append(" - ").append(totalSalary).append("\n");
        }

        String finalReport = report.toString();
        if (finalReport.endsWith("\n")) {
            finalReport = finalReport.substring(0, finalReport.length() - 1);
        }

        return finalReport;
    }
}
