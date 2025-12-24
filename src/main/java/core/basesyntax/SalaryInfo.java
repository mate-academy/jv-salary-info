package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate end = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                if (parts[1].equals(names[i])) {
                    LocalDate entryDate = LocalDate.parse(parts[0], formatter);
                    if (!entryDate.isBefore(start) && !entryDate.isAfter(end)) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        totalSalary += (hours * rate);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(totalSalary);
        }

        return stringBuilder.toString();
    }
}
