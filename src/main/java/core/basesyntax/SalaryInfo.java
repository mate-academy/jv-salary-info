package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKED_HOURS = 2;
    private static final int DATE = 0;
    private static final int PAY_PER_HOUR = 3;
    private static final String SPACE = " ";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int sum = 0;
            for (String row : data) {
                if (row.contains(name)) {
                    String[] cols = row.split(SPACE);
                    if (validDate(cols[DATE], fromDate, toDate)) {
                        sum += Integer.parseInt(cols[WORKED_HOURS])
                                * Integer.parseInt(cols[PAY_PER_HOUR]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name + " - " + sum);
        }
        return builder.toString();
    }

    private static boolean validDate(String date, LocalDate fromDate, LocalDate toDate) {
        LocalDate currDate = LocalDate.parse(date, DATE_FORMAT);
        if ((currDate.isEqual(fromDate) || currDate.isAfter(fromDate))
                && ((currDate.isEqual(toDate) || currDate.isBefore(toDate)))) {
            return true;
        }
        return false;
    }
}
