package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int TARGET_DATE_INDEX = 0;
    private static final int TARGET_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private String separator = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (String name : names) {
            int totalSalary = 0;

            for (String info : data) {
                String[] str = info.split(" ");
                LocalDate date = LocalDate.parse(str[TARGET_DATE_INDEX], DATE_TIME_FORMATTER);
                if (name.equals(str[TARGET_NAME_INDEX])
                        && isDateIncludes(date, localDateFrom, localDateTo)) {
                    totalSalary += Integer.parseInt(str[HOURS_INDEX])
                            * Integer.parseInt(str[SALARY_INDEX]);
                }
            }
            result.append(separator).append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }

    private boolean isDateIncludes(LocalDate target, LocalDate from, LocalDate to) {
        return (target.isAfter(from) || target.equals(from))
                && (target.isBefore(to) || target.equals(to));
    }
}
