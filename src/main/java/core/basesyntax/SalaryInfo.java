package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ");
        builder.append(dateFrom);
        builder.append(" - ");
        builder.append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String item : data) {
                String[] lines = item.split(" ");
                LocalDate currentData = LocalDate.parse(lines[DATE_INDEX], FORMATTER);
                if (((currentData.isAfter(localDateFrom))
                        || (currentData.equals(localDateFrom)))
                        && ((currentData.isBefore(localDateTo))
                        || (currentData.equals(localDateTo)))
                        && (lines[NAME_INDEX].equals(name))) {
                    int sum = Integer.parseInt(lines[HOUR_INDEX])
                            * Integer.parseInt(lines[INCOME_INDEX]);
                    salary = salary + sum;
                }
            }
            builder.append(System.lineSeparator());
            builder.append(name);
            builder.append(" - ");
            builder.append(salary);
        }
        return builder.toString();
    }
}
