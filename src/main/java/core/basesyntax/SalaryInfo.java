package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        Map<String, Integer> salaryMap = new HashMap<>();
        for (String dataRow : data) {
            String[] row = dataRow.split(" ");
            LocalDate date = LocalDate.parse(row[0], formatter);
            String name = row[1];
            int workingHours = Integer.parseInt(row[2]);
            int salary = Integer.parseInt(row[3]);
            if (!date.isBefore(from) && !date.isAfter(to)) {
                int newSalary = salaryMap.getOrDefault(name, 0) + workingHours * salary;
                salaryMap.put(name, newSalary);
            }
        }
        for (String name : names) {
            int salary = salaryMap.getOrDefault(name, 0);
            salaryInfo.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return salaryInfo.toString();
    }
}
