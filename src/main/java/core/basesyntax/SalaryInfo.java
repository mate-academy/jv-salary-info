package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromData = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToData = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        int [] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            String date = parts[0];
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int salaryPerHours = Integer.parseInt(parts[3]);

            LocalDate currentDate = LocalDate.parse(date, formatter);
            if (!currentDate.isBefore(dateFromData) && !currentDate.isAfter(dateToData)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * salaryPerHours;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
