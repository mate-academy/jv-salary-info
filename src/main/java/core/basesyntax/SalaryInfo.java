package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d.M.y");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String line : data) {
                String[] parts = line.split(" ");
                if (!parts[1].equals(names[i])) {
                    continue;
                }
                LocalDate date = LocalDate.parse(parts[0], DATE_TIME_FORMATTER);
                if (date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0) {
                    continue;
                }
                int hours = Integer.parseInt(parts[2]);
                int incomePerHour = Integer.parseInt(parts[3]);
                salaries[i] += incomePerHour * hours;
            }
            stringBuilder.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
