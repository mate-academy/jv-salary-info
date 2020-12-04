package core.basesyntax;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");
        for (String name: names) {
            int salary = 0;
            for (String piece: data) {
                if (LocalDate.parse(Array.get(piece.split(" ", 0), 0).toString(), DATE_FORMAT)
                        .compareTo(LocalDate.parse(dateFrom, DATE_FORMAT)) >= 0 
                        && LocalDate.parse(Array.get(piece.split(" ", 0), 0)
                                .toString(), DATE_FORMAT)
                            .compareTo(LocalDate.parse(dateTo, DATE_FORMAT)) <= 0 
                        && Array.get(piece.split(" ", 0), 1).toString().equals(name)) {
                    salary += getSalary(
                            Integer.parseInt(Array.get(piece.split(" ", 0), 2).toString()),
                            Integer.parseInt(Array.get(piece.split(" ", 0), 3).toString()));
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

    private static int getSalary(int workingHours, int fee) {
        return workingHours * fee;
    }
}
