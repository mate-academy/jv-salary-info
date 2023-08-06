package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateTimeFormatter);

        Map<String, Integer> salaryMap = new HashMap<>();

        for (String record: data) {
            String[] recordParts = record.split(" ");
            LocalDate date = LocalDate.parse(recordParts[0], dateTimeFormatter);
            String nameAtRecord = recordParts[1];
            int hoursAtRecord = Integer.parseInt(recordParts[2]);
            int moneyPerHour = Integer.parseInt(recordParts[3]);

            if (date.isBefore(fromDate) || date.isAfter(toDate)) {
                continue;
            }

            int salary = moneyPerHour * hoursAtRecord;
            salaryMap.put(nameAtRecord,
                    salaryMap.getOrDefault(nameAtRecord, 0) + salary);
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name: names) {
            int salary = salaryMap.getOrDefault(name, 0);
            result.append("\r\n").append(name).append(" - ").append(salary);
        }

        return result.toString();
    }
}
