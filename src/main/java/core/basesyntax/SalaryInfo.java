package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, FORMATTER);;

        for (String name : names) {
            int salary = 0;
            for (String row : data) {
                String[] info = row.trim().split(" ");
                if (info[1].equals(name)) {
                    if (LocalDate.parse(info[0], FORMATTER).isAfter(start.minusDays(1))
                            && LocalDate.parse(info[0], FORMATTER).isBefore(end.plusDays(1))) {
                        salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                    }
                }
            }
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
