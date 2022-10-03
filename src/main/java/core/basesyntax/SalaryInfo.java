package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final int DAYS_BOUNDS = 1;

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
                workingDay = LocalDate.parse(lines[ZERO_INDEX], FORMATTER);
                int salary = 0;
                if (name.equals(lines[FIRST_INDEX])
                        && parseDateFrom.minusDays(DAYS_BOUNDS).isBefore(workingDay)
                        && workingDay.isBefore(parseDateTo.plusDays(DAYS_BOUNDS))) {
                    salary = Integer.parseInt(lines[SECOND_INDEX])
                            * Integer.parseInt(lines[THIRD_INDEX]);
                }
                total = total + salary;
            }
            builder.append(String.valueOf(total));
        }
        return builder.toString();
    }
}
