package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if ((date.isEqual(from) || date.isAfter(from))
                    && (date.isEqual(to) || date.isBefore(to))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += (hoursWorked * hourlyRate);
                        break;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(salaries[i])
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
