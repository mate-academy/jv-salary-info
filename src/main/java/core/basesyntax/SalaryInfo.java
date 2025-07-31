package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int salary = 0;

            for (int j = 0; j < data.length; j++) {
                String[] parts = data[j].split(" ");
                LocalDate date = LocalDate.parse(parts[0], FORMATTER);

                if (!date.isBefore(from) && !date.isAfter(to) && parts[1].equals(names[i])) {
                    salary += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                }
            }
            result.append(names[i]).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString();
    }
}
