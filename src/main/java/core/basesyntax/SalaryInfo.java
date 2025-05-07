package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String NEW_LINE_SEPARATOR = System.lineSeparator();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate from = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate to = LocalDate.parse(dateTo, dateTimeFormatter);
        prepareStringBuilder(stringBuilder, dateFrom, dateTo);
        for (String name : names) {
            stringBuilder.append(name)
                    .append(" - ")
                    .append(getCurrentEarnings(name, data, from, to))
                    .append(NEW_LINE_SEPARATOR);
        }
        return stringBuilder.toString().trim();
    }

    private int getCurrentEarnings(String name, String[] data, LocalDate from, LocalDate to) {
        int earned = 0;
        for (String currEmployeeData : data) {
            String[] splitData = currEmployeeData.split(" ");
            String employeeName = splitData[NAME_INDEX];
            LocalDate date = LocalDate.parse(splitData[DATE_INDEX], dateTimeFormatter);
            Integer hour = Integer.parseInt(splitData[HOUR_INDEX]);
            Integer income = Integer.parseInt(splitData[INCOME_INDEX]);
            if (employeeName.equals(name)
                    && date.compareTo(from) >= 0
                    && date.compareTo(to) <= 0) {
                earned += income * hour;
            }
        }
        return earned;
    }

    private void prepareStringBuilder(StringBuilder sb, String dateFrom, String dateTo) {
        sb.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(NEW_LINE_SEPARATOR);
    }
}
