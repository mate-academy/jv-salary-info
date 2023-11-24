package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOUR_POSITION = 2;
    private static final int SALARY_PER_HOUR_POSITION = 3;
    private static final String SEPARATOR = " - ";
    private static final String REGEX = " ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo);

        LocalDate formatDateFrom = getParseDate(dateFrom);
        LocalDate formatDateTo = getParseDate(dateTo);

        for (String name : names) {
            int salary = 0;
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(SEPARATOR);
            for (String day : data) {

                String[] arrayData = day.split(REGEX);
                String onlyDate = arrayData[DATE_POSITION];
                LocalDate formatDate = getParseDate(onlyDate);

                if (rangeDate(formatDateFrom, formatDateTo, formatDate)) {

                    String nameEmployee = arrayData[NAME_POSITION];
                    if (nameEmployee.equals(name)) {
                        int hour = parseInt(arrayData[HOUR_POSITION]);
                        int payForHours = parseInt(arrayData[SALARY_PER_HOUR_POSITION]);
                        salary += hour * payForHours;
                    }
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }

    private boolean rangeDate(
            LocalDate formatDateFrom,
            LocalDate formatDateTo,
            LocalDate formatDate) {
        return formatDate.isBefore(formatDateTo) && formatDate.isAfter(formatDateFrom)
                || (formatDate.equals(formatDateTo) || formatDate.equals(formatDateFrom));
    }

    private LocalDate getParseDate(String dateFrom) {
        return LocalDate.parse(dateFrom,formatter);
    }
}
