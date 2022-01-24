package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] lineInfo = data[j].split(" ");
                LocalDate date = LocalDate.parse(lineInfo[0], FORMATTER);
                if (names[i].equals(lineInfo[1]) && (date.isAfter(from) && date.isBefore(to)
                        || date.isEqual(to))) {
                    salary += Integer.parseInt(lineInfo[2]) * Integer.parseInt(lineInfo[3]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
