package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateFromParsed = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToParsed = LocalDate.parse(dateTo, dateTimeFormatter);

        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }


        for (String record : data) {
            String[] parts = record.split(" ");
            String dateStr = parts[0];
            String employeeName = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            LocalDate workDate = LocalDate.parse(dateStr, dateTimeFormatter);


            if (!workDate.isBefore(dateFromParsed) && !workDate.isAfter(dateToParsed)) {
                if (salaryMap.containsKey(employeeName)) {
                    int earned = hours * rate;
                    salaryMap.put(employeeName, salaryMap.get(employeeName) + earned);
                }
            }
        }


        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int salary = salaryMap.get(name);
            report.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}


