package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromData = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToDate = LocalDate.parse(dateTo, FORMATTER);

        int datePos = 0;
        int namesPos = 1;
        int hourPos = 2;
        int moneyPos = 3;
        int[] salary = new int[names.length];

        for (String entry : data) {
            String[] splitData = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(splitData[datePos], FORMATTER);

            if (!entryDate.isBefore(dateFromData) && !entryDate.isAfter(dateToDate)) {
                for (int namescounter = 0; namescounter < names.length; namescounter++) {
                    if (names[namescounter].equals(splitData[namesPos])) {
                        salary[namescounter] += Integer.parseInt(splitData[moneyPos]) * Integer.parseInt(splitData[hourPos]);
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " to " + dateTo + "\n");
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i]).append(" - ").append(salary[i]).append("\n");
        }

        return builder.toString();
    }
}
