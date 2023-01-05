package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class SalaryInfo {
    private static final String REPORT_PERIOD_MSG = "Report for period ";
    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yyyy").toFormatter();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();
        int[] salaries = new int[names.length];
        for (String array:data) {
            String[] split = array.split(" ");
            LocalDate arrayDate = LocalDate.parse(split[0], formatter);
            if (arrayDate.isAfter(startDate)
                    && arrayDate.isBefore(endDate) | arrayDate.isEqual(endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(split[1])) {
                        salaries[i] += Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaries[i]);
        }
        return REPORT_PERIOD_MSG + dateFrom + " - " + dateTo + stringBuilder;
    }
}

