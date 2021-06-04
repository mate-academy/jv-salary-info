package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_AMOUNT = 3;
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int amountSum = 0;
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            for (String information : data) {
                String[] totalInformation = information.split(" ");
                if (areInBetween(totalInformation[INDEX_OF_DATE], dateFrom, dateTo)
                        && totalInformation[INDEX_OF_NAME].equals(name)) {
                    amountSum += Integer.parseInt(totalInformation[INDEX_OF_HOURS])
                               * Integer.parseInt(totalInformation[INDEX_OF_AMOUNT]);
                }
            }
            builder.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(amountSum);
            amountSum = 0;
        }
        return builder.toString();
    }

    private boolean areInBetween(String information, String dateFrom, String dateTo) {
        LocalDate currentDate = LocalDate.parse(information, DATE_TIME_FORMATTER);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        return currentDate.isAfter(startDate) && currentDate.isBefore(endDate)
                || currentDate.isAfter(startDate) && currentDate.equals(endDate);
    }

}
