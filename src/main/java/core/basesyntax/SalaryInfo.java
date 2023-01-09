package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String FORMAT_STRING = "dd.MM.yyyy";
    private static final String DELIMITER = " ";
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dateParserFormatter = DateTimeFormatter.ofPattern(FORMAT_STRING);
        try {
            LocalDate localDateFrom = LocalDate.parse(dateFrom, dateParserFormatter);
            LocalDate localDateTo = LocalDate.parse(dateTo, dateParserFormatter);
            builder.append("Report for period ").append(localDateFrom.format(dateParserFormatter))
            .append(" - ").append(localDateTo.format(dateParserFormatter));
            for (String name: names) {
                int totalSum = 0;
                for (String row : data) {
                    String [] input = row.split(DELIMITER);
                    if (name.equals(input[NAME_INDEX])) {
                        LocalDate inputDate = LocalDate.parse(input[DATA_INDEX],
                                dateParserFormatter);
                        if (inputDate.compareTo(localDateFrom) >= 0
                                && inputDate.compareTo(localDateTo) <= 0) {
                            totalSum += Integer.valueOf(input[HOUR_INDEX])
                                                * Integer.valueOf(input[SALARY_INDEX]);
                        }
                    }
                }
                builder.append(System.lineSeparator()).append(name).append(" - ").append(totalSum);
            }
            return builder.toString();
        } catch (DateTimeParseException | NumberFormatException e) {
            throw e;
        }
    }
}
