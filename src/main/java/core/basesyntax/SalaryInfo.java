package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        StringBuilder info = new StringBuilder("Report for period ")
                .append(startDate.format(formatter))
                .append(" - ")
                .append(endDate.format(formatter));

        int[] salaries = new int[names.length];

        for (String records : data) {
            String[] parts = records.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int salaryPerHour = Integer.parseInt(parts[3]);
            if ((workDate.isEqual(startDate) || workDate.isAfter(startDate))
                    && (workDate.isEqual(endDate) || workDate.isBefore(endDate))) {
                   for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += salaryPerHour * hours;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            info.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return info.toString();
    }
}

