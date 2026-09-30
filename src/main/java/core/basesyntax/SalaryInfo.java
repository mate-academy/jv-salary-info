package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int moneyEarned = 0;
            for (String row : data) {
                String[] dataLine = row.split(" ");
                LocalDate date = LocalDate.parse(dataLine[0], formatter);
                if (name.equals(dataLine[1]) && !date.isBefore(from) && !date.isAfter(to)) {
                    int hours = Integer.parseInt(dataLine[2]);
                    int wage = Integer.parseInt(dataLine[3]);
                    moneyEarned += hours * wage;
                }
            }
            builder.append(name).append(" - ")
                    .append(moneyEarned);
            if (!name.equals(names[names.length - 1])) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
