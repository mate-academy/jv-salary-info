package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

public class SalaryInfo {
    public static final DateTimeFormatter DDMMYYYY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean between(LocalDate d, LocalDate from, LocalDate to) {
        return (d.isAfter(from) || d.isEqual(from)) && (d.isBefore(to)|| d.isEqual(to));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int [] salary = new int[names.length];
        LocalDate from = LocalDate.parse(dateFrom, DDMMYYYY);
        LocalDate to = LocalDate.parse(dateTo, DDMMYYYY);
        for (int i = 0; i < data.length; i++) {
            String[] pices = data[i].split(" ");
            LocalDate now = LocalDate.parse(pices[0], DDMMYYYY);
             if ((between(now, from, to))) {
                for (int j = 0; j < names.length; j++) {
                    if (pices[1].equals(names[j])) {
                        salary[j] += parseInt(pices[2]) * parseInt(pices[3]);
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(salary[i]);
        }
        return builder.toString();
    }
}
