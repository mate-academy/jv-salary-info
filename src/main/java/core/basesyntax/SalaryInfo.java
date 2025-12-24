package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate end = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                if (parts[NAME_INDEX].equals(name)) {
                    LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], formatter);
                    if (!entryDate.isBefore(start) && !entryDate.isAfter(end)) {
                        int hours = Integer.parseInt(parts[HOURS_INDEX]);
                        int rate = Integer.parseInt(parts[RATE_INDEX]);
                        totalSalary += (hours * rate);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(totalSalary);
        }

        return stringBuilder.toString();
    }
}
