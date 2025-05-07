package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DIVIDER_DATE = 0;
    private static final int DIVIDER_NAME = 1;
    private static final int DIVIDER_HOURS = 2;
    private static final int DIVIDER_INCOME = 3;

    public String getSalaryInfo(String[]names, String[]data, String dateFrom, String dateTo) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] divideByWhitespace = data[j].split(" ");
                LocalDate ddate = LocalDate.parse(divideByWhitespace[DIVIDER_DATE], FORMATTER);
                LocalDate ddateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate ddateTo = LocalDate.parse(dateTo, FORMATTER);
                if (((ddate.isAfter(ddateFrom) && ddate.isBefore(ddateTo))
                        || ddate.isEqual(ddateFrom) || ddate.isEqual(ddateTo))
                        && divideByWhitespace[DIVIDER_NAME].equals(names[i])) {
                    salary += Integer.parseInt(divideByWhitespace[DIVIDER_HOURS])
                                    * Integer.parseInt(divideByWhitespace[DIVIDER_INCOME]);
                }
            }
            resultBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary);
        }
        return resultBuilder.toString();
    }
}
