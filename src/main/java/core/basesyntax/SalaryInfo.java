package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX_0 = 0;
    private static final int DATE_INDEX_1 = 1;
    private static final int DATE_INDEX_2 = 2;
    private static final int DATE_INDEX_3 = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder reportForPeriod = new StringBuilder();
        reportForPeriod.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String row : data) {
                String date = row.split(" ")[DATE_INDEX_0];
                LocalDate localDateParse = LocalDate.parse(date, FORMATTER);
                if ((localDateParse.isAfter(dateFromFormatted)
                        || localDateParse.isEqual(dateFromFormatted))
                        && (localDateParse.isBefore(dateToFormatted)
                        || localDateParse.isEqual(dateToFormatted))
                        && names[i].equals(row.split(" ")[DATE_INDEX_1])) {
                    salary[i] += Integer.parseInt(row.split(" ")[DATE_INDEX_2])
                            * Integer.parseInt(row.split(" ")[DATE_INDEX_3]);
                }
            }
            reportForPeriod.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return reportForPeriod.toString();
    }
}
