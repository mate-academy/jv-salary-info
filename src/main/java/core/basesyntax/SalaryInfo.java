package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormed = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormed = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();

        for (String name: names) {
            int salary = 0;
            for (String info: data) {
                String[] information = info.split(" ");
                LocalDate dateFormed = LocalDate.parse(information[0], FORMATTER);
                if (!dateFormed.isAfter(dateToFormed) && !dateFormed.isBefore(dateFromFormed)) {
                    if (name.equals(information[1])) {
                        salary += Integer.parseInt(information[2])
                                * Integer.parseInt(information[3]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}
