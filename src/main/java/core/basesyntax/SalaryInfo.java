package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        int[] salaries = new int[names.length];
        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);
            if (workDate.isBefore(from) || workDate.isAfter(to)) {
                continue;
            }
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(employeeName)) {
                    salaries[i] += hoursWorked * hourlyRate;
                }
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {

            report.append(names[i]).append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
