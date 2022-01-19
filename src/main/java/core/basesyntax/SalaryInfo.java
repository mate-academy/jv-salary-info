package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        String[] info = new String[data.length * 4];
        int c = 0;
        for (String d : data) {
            String[] one = d.split(" ");
            info[c] = one[0];
            info[c + 1] = one[1];
            info[c + 2] = one[2];
            info[c + 3] = one [3];
            c += 4;
        }
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < info.length; j += 4) {
                LocalDate date = LocalDate.parse(info[j], FORMATTER);
                if (names[i].equals(info[j + 1]) && (date.isAfter(from) && date.isBefore(to)
                        || date.isEqual(to))) {
                    salary += Integer.parseInt(info[j + 2]) * Integer.parseInt(info[j + 3]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
