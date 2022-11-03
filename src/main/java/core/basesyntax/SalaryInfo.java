package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String string : data) {
                String[] split = string.split(" ");
                LocalDate date = LocalDate.parse(split[0], FORMATTER);
                if (name.equals(split[1]) && !(date.isAfter(to) || date.isBefore(from))) {
                    salary += Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
