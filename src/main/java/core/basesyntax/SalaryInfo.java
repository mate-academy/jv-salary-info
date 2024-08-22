package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String SEPARATOR = System.lineSeparator();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), formatter);

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate workDate = LocalDate.parse(parts[DATE_INDEX], formatter);
            String name = parts[NAME_INDEX];
            int hoursWorked = Integer.parseInt(parts[HOURS_INDEX]);
            int incomePerHour = Integer.parseInt(parts[INCOME_INDEX]);

            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hoursWorked * incomePerHour;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(SEPARATOR);

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(SEPARATOR);
        }

        return report.toString().trim();
    }
}
