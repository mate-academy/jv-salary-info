package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period %s - %s"
            + System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }
        for (String datum : data) {
            String[] info = datum.split(" ");
            LocalDate entryDate = LocalDate.parse(info[0], DATE_FORMATTER);
            if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
                String name = info[1];
                int hoursWorked = Integer.parseInt(info[2]);
                int incomePerHour = Integer.parseInt(info[3]);
                int currentSalary = salaryMap.get(name) + (hoursWorked * incomePerHour);
                salaryMap.put(name, currentSalary);
            }
        }
        StringBuilder report = new StringBuilder(String.format(REPORT_HEADER, dateFrom, dateTo));
        for (String name : names) {
            report.append(name).append(" - ").append(salaryMap.get(name))
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
