package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            builder.append(System.lineSeparator() + name + " - ");
            int salary = 0;
            for (String line : data) {
                String[] splited = line.split(" ");
                String dateOnLine = splited[DATE_INDEX];
                String nameOnLine = splited[NAME_INDEX];
                String hoursOnLine = splited[HOURS_INDEX];
                String incomeOnLine = splited[INCOME_INDEX];
                LocalDate localDate = LocalDate.parse(dateOnLine, DATE_FORMATTER);
                if ((localDate.isAfter(fromDate) && localDate.isBefore(toDate)
                        || localDate.equals(toDate)) && nameOnLine.equals(name)) {
                    int hours = Integer.parseInt(hoursOnLine);
                    int income = Integer.parseInt(incomeOnLine);
                    salary += hours * income;
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
