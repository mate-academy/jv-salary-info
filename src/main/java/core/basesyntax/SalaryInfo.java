package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), formatter);

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);

            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hoursWorked * incomePerHour;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
