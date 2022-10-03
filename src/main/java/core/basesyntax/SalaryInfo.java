package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int HOUR_WAGE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parseDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate workingDay;
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int total = 0;
            for (String line : data) {
                String[] lines = line.split(" ");
                workingDay = LocalDate.parse(lines[WORKING_DAY_INDEX], FORMATTER);
                int salary = 0;
                if (name.equals(lines[NAME_INDEX])
                        && parseDateFrom.isBefore(workingDay) | parseDateFrom.isEqual(workingDay)
                        && workingDay.isBefore(parseDateTo) | workingDay.isEqual(parseDateTo)) {
                    salary = Integer.parseInt(lines[WORKING_HOURS_INDEX])
                            * Integer.parseInt(lines[HOUR_WAGE_INDEX]);
                }
                total = total + salary;
            }
            builder.append(total);
        }
        return builder.toString();
    }
}
