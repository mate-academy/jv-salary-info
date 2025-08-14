package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                String recordDateStr = parts[0];
                String recordName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                LocalDate recordDate = LocalDate.parse(recordDateStr, formatter);

                if (name.equals(recordName)
                        && !recordDate.isBefore(startDate)
                        && !recordDate.isAfter(endDate)) {
                    totalSalary += hours * rate;
                }
            }

            reportBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }

        return reportBuilder.toString();
    }
}
