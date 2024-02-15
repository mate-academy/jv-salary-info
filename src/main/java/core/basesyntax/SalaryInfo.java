package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        int[] salaries = new int[names.length];
        for (String item : data) {
            String[] items = item.split(" ");
            LocalDate itemDate = LocalDate.parse(items[0], FORMATTER);
            if (!itemDate.isBefore(fromDate) && !itemDate.isAfter(toDate)) {
                String name = items[1];
                int hours = Integer.parseInt(items[2]);
                int incomePerHour = Integer.parseInt(items[3]);
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * incomePerHour;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i]).append(" - ").append(salaries[i])
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
