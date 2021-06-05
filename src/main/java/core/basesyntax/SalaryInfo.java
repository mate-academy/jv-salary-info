package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        return String.format("Report for period %s - %s", dateFrom, dateTo)
                + calculateSalaries(names,
                        data,
                        LocalDate.parse(dateFrom, FORMATTER),
                        LocalDate.parse(dateTo, FORMATTER));
    }

    private String calculateSalaries(String[] names, String[] data, LocalDate from, LocalDate to) {
        StringBuilder result = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] fields = line.split(" ");
                if (!name.equals(fields[1])) {
                    continue;
                }
                LocalDate date = LocalDate.parse(fields[0], FORMATTER);
                if (date.compareTo(from) < 0 || date.compareTo(to) > 0) {
                    continue;
                }
                salary += Integer.parseInt(fields[2]) * Integer.parseInt(fields[3]);
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
