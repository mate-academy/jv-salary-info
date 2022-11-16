package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOUR = 2;
    private static final int INCOME = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate date1 = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate date2 = LocalDate.parse(dateTo, FORMATTER);
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            salary[i] = 0;
            for (int j = 0; j < data.length; j++) {
                String line = data[j];
                String[] lines = line.split(" ");
                LocalDate dateLine = LocalDate.parse(lines[DATE], FORMATTER);
                if (((dateLine.isAfter(date1)) || (dateLine.equals(date1)))
                        && ((dateLine.isBefore(date2)) || (dateLine.equals(date2)))) {
                    if (lines[NAME].equals(names[i])) {
                        int sum = Integer.parseInt(lines[HOUR]) * Integer.parseInt(lines[INCOME]);
                        salary[i] = salary[i] + sum;
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ");
        builder.append(dateFrom);
        builder.append(" - ");
        builder.append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator());
            builder.append(names[i]);
            builder.append(" - ");
            builder.append(salary[i]);
        }
        return builder.toString();
    }
}
