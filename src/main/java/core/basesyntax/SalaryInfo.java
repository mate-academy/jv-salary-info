package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate parseDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String stringOfData : data) {
                String[] splitData = stringOfData.split(" ");
                LocalDate splitDataDate = LocalDate.parse(splitData[INDEX_OF_DATE], DATE_FORMATTER);
                if ((splitDataDate.isEqual(parseDateFrom)
                        || splitDataDate.isAfter(parseDateFrom))
                        && (splitDataDate.isEqual(parseDateTo)
                        || splitDataDate.isBefore(parseDateTo))
                        && splitData[INDEX_OF_NAME].equals(name)) {
                    moneyEarned += Integer.parseInt(splitData[INDEX_OF_HOURS])
                            * Integer.parseInt(splitData[INDEX_OF_INCOME_PER_HOUR]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(moneyEarned);
        }
        return builder.toString();
    }
}

