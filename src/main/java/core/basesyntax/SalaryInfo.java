package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int HOURS_POSITION = 2;
    public static final int RATE_POSITION = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        int[] earned = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String line: data) {
            String[] segments = line.split(" ");
            LocalDate date = LocalDate.parse(segments[DATE_POSITION], DATE_FORMAT);
            String name = segments[NAME_POSITION];
            int hours = Integer.parseInt(segments[HOURS_POSITION]);
            int rate = Integer.parseInt(segments[RATE_POSITION]);

            if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (name.equals(names[i])) {
                        earned[i] += hours * rate;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(earned[i]);
        }
        return result.toString();
    }
}
