package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE = 0;
    public static final int NAME = 1;
    public static final int WORK_HOURS = 2;
    public static final int PAY_FOR_HOUR = 3;
    public static final String REGEX = "\\s+";
    public static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int earnByName = 0;
            for (String someData : data) {
                String[] arrayWithDate = someData.split(REGEX);
                LocalDate date = LocalDate.parse(arrayWithDate[DATE], DATE_TIME_FORMATTER);
                if (arrayWithDate[NAME].equals(name)) {
                    if (!date.isBefore(localDateFrom) && !date.isAfter(localDateTo)) {
                        earnByName += Integer.parseInt(arrayWithDate[WORK_HOURS])
                                * Integer.parseInt(arrayWithDate[PAY_FOR_HOUR]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(earnByName);
        }
        return stringBuilder.toString();
    }
}
