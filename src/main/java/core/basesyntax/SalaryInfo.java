package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final byte DATE_PLACE = 0;
    private static final byte NAME_PLACE = 1;
    private static final byte DAYS_PLACE = 2;
    private static final byte INCOME_PLACE = 3;
    private static final String SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateBegin = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder builder = new StringBuilder();
        int sum;
        LocalDate curDate;

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            sum = 0;
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");

            for (String line : data) {
                String[] value = line.split(SEPARATOR);
                curDate = LocalDate.parse(value[DATE_PLACE],DATE_TIME_FORMATTER);

                if (name.equals(value[NAME_PLACE])
                        && (curDate.isAfter(dateBegin) && curDate.isBefore(dateEnd)
                        || curDate.isEqual(dateBegin) || curDate.isEqual(dateEnd))) {
                    sum += Integer.parseInt(value[DAYS_PLACE])
                            * Integer.parseInt(value[INCOME_PLACE]);
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }
}
