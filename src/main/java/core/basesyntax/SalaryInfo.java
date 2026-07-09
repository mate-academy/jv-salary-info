package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        int[] salaries = new int[names.length];
        StringBuilder stringBuilder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String record : data) {
            String[] parts = record.split(" ");
            String date = parts[DATE_INDEX];
            String name = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int rate = Integer.parseInt(parts[RATE_INDEX]);
            LocalDate recordDate = LocalDate.parse(date, formatter);
            if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {
                int index = Arrays.asList(names).indexOf(name);
                if (index >= 0) {
                    salaries[index] += hours * rate;
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i])
                    .append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
