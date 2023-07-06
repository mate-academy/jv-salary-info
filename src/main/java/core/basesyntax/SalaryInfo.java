package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, PATTERN);
        LocalDate toDate = LocalDate.parse(dateTo, PATTERN);

        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;

            for (String line : data) {
                String[] employeeData = line.split(SEPARATOR);

                LocalDate date = LocalDate.parse(employeeData[DATE_INDEX], PATTERN);
                String employeeName = employeeData[NAME_INDEX];

                if (name.equals(employeeName) && isAfterOrEqual(fromDate, date)
                        && isBeforeOrEqual(toDate, date)) {
                    int hours = Integer.parseInt(employeeData[HOURS_INDEX]);
                    int incomePerHour = Integer.parseInt(employeeData[INCOME_PER_HOUR_INDEX]);
                    salary += hours * incomePerHour;
                }
            }

            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return result.toString();
    }

    private boolean isBeforeOrEqual(LocalDate date, LocalDate compareToDate) {
        if (date == null || compareToDate == null) {
            return false;
        }
        return !compareToDate.isAfter(date);
    }

    private boolean isAfterOrEqual(LocalDate date, LocalDate compareToDate) {
        if (date == null || compareToDate == null) {
            return false;
        }
        return !compareToDate.isBefore(date);
    }
}
