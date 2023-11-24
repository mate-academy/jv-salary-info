package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DAYS = 0;
    public static final int WORKER_NAME = 1;
    public static final int HOURS = 2;
    public static final int HOURLY_PAY = 3;
    public static final String REGEX = " ";
    public static final String REGEX_RESULT = " - ";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + REGEX_RESULT + dateTo + System.lineSeparator());
        for (String employee : names) {
            int salary = 0;
            for (String actualData : data) {
                String[] dateSplit = actualData.split(REGEX);
                if (employee.equals(dateSplit[WORKER_NAME])
                        && isBetween(dateFrom, dateTo, dateSplit[DAYS])) {
                    salary += parseInt(dateSplit[HOURLY_PAY]) * parseInt(dateSplit[HOURS]);
                }
            }
            builder.append(employee).append(REGEX_RESULT)
                    .append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private boolean isBetween(String dateFrom, String dateTo, String forCompare) {
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate end = LocalDate.parse(dateTo, formatter);
        LocalDate actual = LocalDate.parse(forCompare, formatter);
        return actual.compareTo(start) >= 0 && actual.compareTo(end) <= 0;
    }
}
