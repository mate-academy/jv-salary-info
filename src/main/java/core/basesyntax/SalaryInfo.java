package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX_0 = 0;
    private static final int DATE_INDEX_1 = 1;
    private static final int DATE_INDEX_2 = 2;
    private static final int DATE_INDEX_3 = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder;

        LocalDate dateFromLocal;
        LocalDate dateToLocal;
        LocalDate dateActualLocal;
        String[] split = new String[4];
        int salary;
        dateFromLocal = LocalDate.parse(dateFrom, formatter);
        dateToLocal = LocalDate.parse(dateTo, formatter);
        builder = new StringBuilder();
        if (dateFromLocal.isAfter(dateToLocal)) {
            return null;
        }
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {

            builder.append(System.lineSeparator())
                    .append(name);
            salary = DATE_INDEX_0;
            for (String dates : data) {
                split = dates.split(" ");
                dateActualLocal = LocalDate.parse(split[DATE_INDEX_0], formatter);
                if (((dateFromLocal.isBefore(dateActualLocal)
                        && dateActualLocal.isBefore(dateToLocal))
                        || dateFromLocal.isEqual(dateActualLocal)
                        || dateActualLocal.isEqual(dateToLocal))
                        && name.equals(split[DATE_INDEX_1])) {
                    salary += Integer.parseInt(split[DATE_INDEX_2])
                            * Integer.parseInt(split[DATE_INDEX_3]);
                }

            }
            builder.append(" - ")
                    .append(salary);

        }
        return builder.toString();
    }

}
