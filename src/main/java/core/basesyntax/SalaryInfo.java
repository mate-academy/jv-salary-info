package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] part = datum.split(" ");
                LocalDate localDate = LocalDate.parse(part[DATE_INDEX], formatter);
                String employeeName = part[NAME_INDEX];
                if (employeeName.equals(name)
                        && !localDate.isBefore(localDateFrom)
                        && !localDate.isAfter(localDateTo)) {
                    salary += Integer.parseInt(part[SALARY_INDEX])
                            * Integer.parseInt(part[WORKED_HOURS_INDEX]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
