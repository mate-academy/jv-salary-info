package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate toDate = LocalDate.parse(dateTo.trim(), formatter);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate recordDate = LocalDate.parse(parts[0], formatter);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if (!recordDate.isBefore(fromDate) && !recordDate.isBefore(fromDate)) {

                for (int i = 0; i < data.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += hourlyRate * hourlyRate;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]).append("\n");
        }
        return report.toString();
    }
}

