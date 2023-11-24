package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateForm = parse(dateFrom, FORMATTER);
        LocalDate localDateTo = parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String period : data) {
                String[] dataElement = period.split(" ");
                LocalDate day = parse(dataElement[0], FORMATTER);
                if ((dataElement[1].equals(name))
                        && (day.isAfter(localDateForm)
                        || day.isEqual(localDateForm))
                        && (day.isBefore(localDateTo)
                        || day.isEqual(localDateTo))) {
                    salary += Integer.parseInt(dataElement[2])
                            * Integer.parseInt(dataElement[3]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
