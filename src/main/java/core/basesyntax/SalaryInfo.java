package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int ONE_DAY = 1;
    private static final int DATE_IN_DATA = 0;
    private static final int NAME_IN_DATA = 1;
    private static final int HOURS_IN_DATA = 2;
    private static final int HOURLY_INCOME_IN_DATA = 3;
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String inputValue : data) {
                String[] dataInput = inputValue.split(" ");
                if (name.equals(dataInput[NAME_IN_DATA])
                        && isBetween(dataInput[DATE_IN_DATA], dateFrom, dateTo)) {
                    salary += Integer.parseInt(dataInput[HOURLY_INCOME_IN_DATA])
                            * Integer.parseInt(dataInput[HOURS_IN_DATA]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    private boolean isBetween(String inputDate, String dateFrom, String dateTo) {
        return LocalDate.parse(inputDate, DATE_TIME_FORMATTER)
               .isAfter(LocalDate.parse(dateFrom,DATE_TIME_FORMATTER).minusDays(ONE_DAY))
               && LocalDate.parse(inputDate, DATE_TIME_FORMATTER)
               .isBefore(LocalDate.parse(dateTo,DATE_TIME_FORMATTER).plusDays(ONE_DAY));
    }
}
