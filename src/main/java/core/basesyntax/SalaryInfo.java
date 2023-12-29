package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int DATE_POS = 0;
    private static final int NAME_POS = 1;
    private static final int TIME_POS = 2;
    private static final int SALARY_PER_HOUR_POS = 3;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate formatDateFrom = getParseDate(dateFrom);
        LocalDate formatDateTo = getParseDate(dateTo);
        for (String name : names) {
            int salary = 0;
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            for (String day : data) {
                String[] arrayData = day.split(" ");
                String date = arrayData[DATE_POS];
                LocalDate formatDate = getParseDate(date);
                if (rangeDate(formatDateFrom, formatDateTo, formatDate)) {
                    String nameEmployee = arrayData[NAME_POS];
                    if (nameEmployee.equals(name)) {
                        int hour = parseInt(arrayData[TIME_POS]);
                        int payForHours = parseInt(arrayData[SALARY_PER_HOUR_POS]);

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


