package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKED_HOURS = 2;
    private static final int DATE = 0;
    private static final int PAY_PER_HOUR = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int sum = 0;
            for (String row : data) {
                if (row.contains(name)) {
                    System.out.println(row);
                    String[] cols = row.split(" ");
                    if (validDate(cols[DATE], dateFrom, dateTo)) {
                        sum += Integer.parseInt(cols[WORKED_HOURS])
                                * Integer.parseInt(cols[PAY_PER_HOUR]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name + " - " + sum);
        }
        System.out.println(builder.toString());
        return null;
    }

    private static boolean validDate(String date, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currDate = LocalDate.parse(date, formatter);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        if ((currDate.isEqual(fromDate) || currDate.isAfter(fromDate))
                && ((currDate.isEqual(toDate) || currDate.isBefore(toDate)))) {
            return true;
        }
        return false;
    }
}
