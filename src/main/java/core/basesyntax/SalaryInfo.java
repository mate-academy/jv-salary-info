package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        for (String name: names) {
            int salary = 0;
            for (String row: data) {
                String[] info = row.split(" ", 0);
                if (LocalDate.parse(info[0], DATE_FORMAT)
                        .compareTo(LocalDate.parse(dateFrom, DATE_FORMAT)) >= 0 
                        && LocalDate.parse(info[0], DATE_FORMAT)
                            .compareTo(LocalDate.parse(dateTo, DATE_FORMAT)) <= 0 
                        && info[1].equals(name)) {
                    salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                }
            }
            builder.append(name).append(" - ")
                    .append(salary);
            if (!name.equals(names[names.length - 1])) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
