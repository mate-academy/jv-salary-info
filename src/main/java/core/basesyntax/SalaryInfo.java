package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                LocalDate dateFromData = LocalDate.parse(arrDates[0], FORMATTER);
                if (localDateFrom.compareTo(dateFromData) <= 0
                        && localDateTo.compareTo(dateFromData) >= 0
                        && name.equals(arrDates[1])) {
                    salary += (Integer.parseInt(arrDates[2])
                            * Integer.parseInt(arrDates[3]));
                }
            }
            result.append('\n').append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
