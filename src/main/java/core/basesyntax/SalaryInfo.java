package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate to = LocalDate.parse(dateTo.trim(), formatter);

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (!date.isBefore(from) && !date.isAfter(to)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * rate;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator());
            report.append(names[i]).append(" - ").append(salaries[i]);
        }

        return report.toString();
    }
}
