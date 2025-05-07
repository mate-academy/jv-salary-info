package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate currentDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate currentDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder currentRows = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String rows : data) {
                String[] rowsDate = rows.split(" ");
                LocalDate currentDate = LocalDate.parse(rowsDate[DATE_INDEX], FORMATTER);
                if (currentDate.compareTo(currentDateFrom) >= 0
                        && currentDate.compareTo(currentDateTo) <= 0
                        && rowsDate[NAME_INDEX]
                        .equals(name)) {
                    salary += (Integer.parseInt(rowsDate[HOURS_INDEX]))
                            * (Integer.parseInt(rowsDate[SALARY_PER_HOUR_INDEX]));
                }
            }
            currentRows.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(salary);
        }
        return currentRows.toString();
    }
}
