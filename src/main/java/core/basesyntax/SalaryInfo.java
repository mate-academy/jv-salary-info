package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder().append("Report for period ")
                .append(startDate.format(formatter))
                .append(" - ")
                .append(endDate.format(formatter))
                .append(System.lineSeparator());
        for (String entry : data) {
            String[] parts = entry.split(" ");
            String name = parts[1];
            LocalDate entryDate = LocalDate.parse(parts[0], formatter);
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
            if (entryDate.isAfter(startDate.minusDays(1))
                    && entryDate.isBefore(endDate.plusDays(1))
                    && checkName(name, names)) {
                builder.append(name)
                        .append(" - ");
                int salary = rate * hours;
                builder.append(salary)
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    public boolean checkName(String firstName, String[] names) {
        for (String name : names) {
            if (name.equals(firstName)) {
                return true;
            }
        }
        return false;
    }
}
