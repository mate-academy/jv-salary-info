package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        final LocalDate toDate = LocalDate.parse(dateTo, formatter);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[0], formatter);

            if (!currentDate.isBefore(fromDate) && !currentDate.isAfter(toDate)) {
                String employeeName = parts[1];
                int moneyEarned = Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += moneyEarned;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return report.toString();
    }
}
