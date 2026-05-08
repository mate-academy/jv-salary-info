package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate recordDate = LocalDate.parse(parts[0], FORMATTER);
                String recordName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);
                if (recordName.equals(name)
                        && !recordDate.isBefore(start)
                        && !recordDate.isAfter(end)) {
                    totalSalary += hours * rate;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();
    }
}
