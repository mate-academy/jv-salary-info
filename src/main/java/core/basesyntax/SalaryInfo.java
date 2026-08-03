package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();

        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] parts = record.split("\\s+");

                LocalDate currentDate = LocalDate.parse(parts[0], formatter);

                String employeeName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int salaryPerHour = Integer.parseInt(parts[3]);

                boolean isInPeriod = !currentDate.isBefore(from) && !currentDate.isAfter(to);

                if (name.equals(employeeName) && isInPeriod) {
                    totalSalary += hours * salaryPerHour;

                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();

    }
}
