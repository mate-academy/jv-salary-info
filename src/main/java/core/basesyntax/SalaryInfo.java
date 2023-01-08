package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String FORMAT_STRING = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        final DataParserFormatter dataParser = new DataParserFormatter(FORMAT_STRING);
        try {
            LocalDate localDateFrom = dataParser.parseDate(dateFrom);
            LocalDate localDateTo = dataParser.parseDate(dateTo);
            builder.append("Report for period ").append(dataParser.formatDate(localDateFrom))
            .append(" - ").append(dataParser.formatDate(localDateTo));
            for (String name: names) {
                int totalSum = 0;
                for (String row: data) {
                    String [] input = row.split(" ");
                    if (name.equals(input[1])) {
                        LocalDate inputDate = dataParser.parseDate(input[0]);
                        if (inputDate.compareTo(localDateFrom) >= 0
                                && inputDate.compareTo(localDateTo) <= 0) {
                            totalSum += dataParser.parseInt(input[2])
                                                * dataParser.parseInt(input[3]);
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
