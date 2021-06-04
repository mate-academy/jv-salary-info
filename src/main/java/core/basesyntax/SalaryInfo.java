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
        StringBuilder report = new StringBuilder();
        int amountSum = 0;
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            for (String information : data) {
                String[] totalInformation = information.split(" ");
                if (areInBetween(LocalDate.parse(totalInformation[INDEX_OF_DATE],
                        DATE_TIME_FORMATTER), LocalDate.parse(dateFrom, DATE_TIME_FORMATTER),
                        LocalDate.parse(dateTo, DATE_TIME_FORMATTER))
                        && totalInformation[INDEX_OF_NAME].equals(name)) {
                    amountSum += Integer.parseInt(totalInformation[INDEX_OF_HOURS])
                            * Integer.parseInt(totalInformation[INDEX_OF_AMOUNT]);
                }
            }
            report.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(amountSum);
            amountSum = 0;
        }
        return report.toString();
    }

    private boolean areInBetween(LocalDate currentDate, LocalDate dateFrom, LocalDate dateTo) {
        return currentDate.isAfter(dateFrom)
                && currentDate.isBefore(dateTo)
                || currentDate.isAfter(dateFrom)
                && currentDate.equals(dateTo);
    }
}
