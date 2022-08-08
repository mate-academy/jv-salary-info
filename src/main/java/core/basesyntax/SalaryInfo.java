package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_REVENUE_PER_HOUR = 2;
    private static final int INDEX_OF_WORKING_HOURS = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        String[] arrDates;
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {

            int salary = 0;
            for (String dates : data) {
                arrDates = dates.split(" ");
                LocalDate dateFromData = LocalDate.parse(arrDates[INDEX_OF_DATE], FORMATTER);
                if (localDateFrom.compareTo(dateFromData) <= 0
                        && localDateTo.compareTo(dateFromData) >= 0
                        && name.equals(arrDates[INDEX_OF_NAME])) {
                    salary += (Integer.parseInt(arrDates[INDEX_OF_REVENUE_PER_HOUR])
                            * Integer.parseInt(arrDates[INDEX_OF_WORKING_HOURS]));
                }
            }
            result.append('\n').append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
