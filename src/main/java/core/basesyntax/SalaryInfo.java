package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginOfPeriod = LocalDate.parse(dateFrom, DATE_FORMAT).minusDays(1);
        LocalDate endOfPeriod = LocalDate.parse(dateTo, DATE_FORMAT).plusDays(1);
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalAmount = 0;
            for (String dataLine : data) {
                String[] dataLineAsArray = dataLine.split(" ");
                String particularDay = dataLineAsArray[DATE_INDEX];
                LocalDate particularDate = LocalDate.parse(particularDay, DATE_FORMAT);
                if (dataLine.contains(name) && particularDate.isAfter(beginOfPeriod)
                        && particularDate.isBefore(endOfPeriod)) {
                    int workingHours = Integer.parseInt(dataLineAsArray[WORKING_HOURS_INDEX]);
                    int paymentPerHour = Integer.parseInt(dataLineAsArray[INCOME_PER_HOUR_INDEX]);
                    totalAmount += workingHours * paymentPerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalAmount);
        }
        return stringBuilder.toString().trim();
    }
}
