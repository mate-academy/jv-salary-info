package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int MONEY = 3;
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] record;
        int sum;
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            report.append(System.lineSeparator());
            sum = 0;
            for (String datum : data) {
                record = datum.split(" ");
                if ((record[NAME].equals(name))
                        && isDataOk(record[DATE], dateFrom, dateTo)) {
                    sum += Integer.parseInt(record[HOURS])
                            * Integer.parseInt(record[MONEY]);

                }
            }
            report.append(name)
                    .append(" - ")
                    .append(sum);
        }
        return report.toString();
    }

    public boolean isDataOk(String date, String dateFrom, String dateTo) {
        return (((LocalDate.parse(date, PATTERN).isBefore(LocalDate.parse(dateTo, PATTERN)))
                || (LocalDate.parse(date, PATTERN).isEqual(LocalDate.parse(dateTo, PATTERN))))
                && ((LocalDate.parse(date, PATTERN).isAfter(LocalDate.parse(dateFrom, PATTERN)))
                || (LocalDate.parse(date, PATTERN).isEqual(LocalDate.parse(dateFrom, PATTERN)))));
    }
}
