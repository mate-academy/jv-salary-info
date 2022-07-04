package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String lines:data) {
                String[] split = lines.split(" ");
                LocalDate currentDate = LocalDate.parse(split[0], FORMATTER);
                if (currentDate.compareTo(localDateFrom) >= 0
                        && currentDate.compareTo(localDateTo) <= 0
                        && split[1].equals(name)) {
                    salary = salary + Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
