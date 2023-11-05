package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int QUANTITY_POSITION = 2;
    private static final int AMOUNT_POSITION = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate fromDateParsed = LocalDate.parse(dateFrom, formatter);
        LocalDate toDateParsed = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int totalResources = 0;
            for (String dataLine : data) {
                String[] dataArray = dataLine.split(" ");
                LocalDate dateParsed = LocalDate.parse(dataArray[DATE_POSITION], formatter);
                String dataName = dataArray[NAME_POSITION];
                if (!name.equals(dataName)) {
                    continue;
                }
                if (dateParsed.isAfter(fromDateParsed) || dateParsed.isBefore(toDateParsed)) {
                    continue;
                }
                totalResources += Integer.parseInt(dataArray[QUANTITY_POSITION])
                        * Integer.parseInt(dataArray[AMOUNT_POSITION]);
            }
            builder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalResources);
        }
        return builder.toString();
    }
}