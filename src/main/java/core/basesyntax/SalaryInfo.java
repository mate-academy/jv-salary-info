package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        LocalDate start = getDate(dateFrom);
        LocalDate end = getDate(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] information = info.split(" ");
                    if (getDate(information[0]).isAfter(start.minusDays(1))
                                && getDate(information[0]).isBefore(end.plusDays(1))) {
                        salary += Integer.parseInt(information[2])
                                * Integer.parseInt(information[3]);
                    }
                }
            }
            result.append(name).append(" - ").append(salary).append("\n");
        }
        return result.toString().trim();
    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}

