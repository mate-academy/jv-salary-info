package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final String REPORT_HEADER = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOUR_POSITION = 2;
    private static final int MONEY_PER_HOUR_POSITION = 3;
    private static final String SPACE = " ";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate date1 = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate date2 = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append(REPORT_HEADER).append(dateFrom).append(SEPARATOR)
                .append(dateTo);

        for (var name : names) {
            builder.append(System.lineSeparator()).append(name).append(SEPARATOR);
            int totalIncome = 0;
            for (var d : data) {
                var info = d.split(SPACE);
                var currentDate = LocalDate.parse(info[DATE_POSITION], FORMATTER);
                if ((currentDate.equals(date1) || currentDate.isAfter(date1))
                        && (currentDate.equals(date2) || currentDate.isBefore(date2))
                        && name.equals(info[NAME_POSITION])) {
                    totalIncome += Integer.parseInt(info[HOUR_POSITION])
                            * Integer.parseInt(info[MONEY_PER_HOUR_POSITION]);
                }
            }
            builder.append(totalIncome);
        }
        return builder.toString();
    }
}
