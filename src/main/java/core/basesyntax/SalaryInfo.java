package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String DATA_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMATTER);
        LocalDate start = LocalDate.parse(dateFrom, dateFormatter).minusDays(1);
        LocalDate end = LocalDate.parse(dateTo, dateFormatter).plusDays(1);
        int earnedMoney = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            for (String datum : data) {
                String[] splitArray = datum.split(DATA_SEPARATOR);
                if (splitArray[NAME_INDEX].equals(name)
                        && (LocalDate.parse(splitArray[DATE_INDEX], dateFormatter).isAfter(start)
                        && LocalDate.parse(splitArray[DATE_INDEX], dateFormatter).isBefore(end))) {
                    earnedMoney += Integer.parseInt(splitArray[HOURS_INDEX])
                            * Integer.parseInt(splitArray[RATE_INDEX]);
                }
            }
            builder.append(name).append(" - ")
                    .append(earnedMoney).append(System.lineSeparator());
            earnedMoney = 0;
        }
        return builder.toString().trim();
    }
}
