package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        int[] totalSalaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(parts[0], formatter);
            if (entryDate.isBefore(from) || entryDate.isAfter(to)) {
                continue;
            }

            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int salaryPerHour = Integer.parseInt(parts[3]);
            int total = hours * salaryPerHour;

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    totalSalaries[i] += total;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder
                    .append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(totalSalaries[i]);
        }

        return builder.toString();
    }
}
