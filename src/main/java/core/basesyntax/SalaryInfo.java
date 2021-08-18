package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INFO = 2;
    private static final int PRICE_PER_HOUR_INFO = 3;
    private static final int DIFFERENCE = 1;
    private static String FORMATTER = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER);

        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.getProperty("line.separator"));
        for (String name : names) {
            int bufferSum = 0;
            for (String datum : data) {
                String[] personalData = datum.split(" ");
                if (name.equals(personalData[NAME_INDEX])
                        && isValidDate(dateFrom, personalData, dateTo, FORMATTER)
                ) {
                    bufferSum += Integer.parseInt(personalData[HOURS_INFO])
                            * Integer.parseInt(personalData[PRICE_PER_HOUR_INFO]);
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(bufferSum)
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }

    public boolean isValidDate(String dateFrom,
                               String[] personalData,
                               String dateTo,
                               String formatter) {
        return LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(formatter))
                .isBefore(LocalDate.parse(
                        personalData[DATE_INDEX], DateTimeFormatter.ofPattern(formatter)))
                && LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(formatter))
                .plusDays(DIFFERENCE).isAfter((LocalDate.parse(
                        personalData[DATE_INDEX], DateTimeFormatter.ofPattern(formatter))));
    }
}
