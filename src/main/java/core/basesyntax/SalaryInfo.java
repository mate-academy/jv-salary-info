package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        LocalDate start = getStartDate(dateFrom);
        LocalDate end = getEndDate(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] information = info.split(" ");
                    if (getStartDate(information[0]).isAfter(start)
                                && getEndDate(information[0]).isBefore(start)) {
                        salary += Integer.parseInt(information[2])
                                * Integer.parseInt(information[3]);
                    }
                }
            }
            result.append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    private LocalDate getEndDate(String dateTo) {
        return LocalDate.parse(dateTo, FORMATTER);
    }

    private LocalDate getStartDate(String dateFrom) {
        return LocalDate.parse(dateFrom, FORMATTER);
    }
}

