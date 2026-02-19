package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String entry : data) {
                String[] parts = entry.split(" ");

                LocalDate entryDate = LocalDate.parse(parts[0], FORMATTER);
                String entryName = parts[1];

                if (entryName.equals(name)
                        && !entryDate.isBefore(start)
                        && !entryDate.isAfter(end)) {

                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    totalSalary += hours * rate;
                }
            }

            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return report.toString();

    }
}
