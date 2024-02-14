package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        int[] salaries = new int[names.length];
        for (String entry : data) {
            String[] parts = entry.split(" ");
            String name = parts[1];
            LocalDate entryDate = LocalDate.parse(parts[0], formatter);
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
            if (entryDate.isAfter(startDate.minusDays(1))
                    && entryDate.isBefore(endDate.plusDays(1))) {
                int index = indexOfName(name, names);
                if (index != -1) {
                    salaries[index] += rate * hours;
                }
            }
        }
        return generateReport(startDate, endDate, names, salaries);
    }

    private int indexOfName(String name, String[] names) {
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                return i;
            }
        }
        return -1;
    }

    private String generateReport(LocalDate startDate, LocalDate endDate, String[] names, int[] salaries) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(startDate.format(formatter))
                .append(" - ")
                .append(endDate.format(formatter))
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
