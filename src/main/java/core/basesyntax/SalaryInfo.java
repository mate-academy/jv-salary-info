package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int NAME_ORDER = 1;
    private static final int DATE_ORDER = 0;
    private static final int HOUR_ORDER = 2;
    private static final int SALARY_ORDER = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        fromDate = fromDate.minusDays(1);
        toDate = toDate.plusDays(1);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] array = datum.split(" ");
                LocalDate check = LocalDate.parse(array[DATE_ORDER], formatter);
                if (name.equals(array[NAME_ORDER]) && check.isAfter(fromDate)
                        && check.isBefore(toDate)) {
                    salary += Integer.parseInt(array[HOUR_ORDER])
                            * Integer.parseInt(array[SALARY_ORDER]);
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
