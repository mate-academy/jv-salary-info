package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);

        Map<String, Integer> salaryMap = new HashMap<>();
        for (String employee : names) {
            salaryMap.put(employee, 0);
        }

        for (String record : data) {
            String[] splitData = record.split(" ");
            LocalDate workDate = LocalDate.parse(splitData[0], DATE_FORMAT);
            String employeeName = splitData[1];

            int hoursWorked = Integer.parseInt(splitData[2]);
            int hourlyRate = Integer.parseInt(splitData[3]);

            if (salaryMap.containsKey(employeeName)
                    && !workDate.isBefore(startDate)
                    && !workDate.isAfter(endDate)) {
                int currentSalary = salaryMap.get(employeeName);
                salaryMap.put(employeeName, currentSalary + (hoursWorked * hourlyRate));
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String employee : names) {
            report.append("\n").append(employee).append(" - ").append(salaryMap.get(employee));
        }

        return report.toString();
    }
}
