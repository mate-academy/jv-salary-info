package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int INDEX_OF_DAY = 0;
    public static final int INDEX_OF_NAME = 1;
    public static final int INDEX_OF_HOURS = 2;
    public static final int INDEX_OF_MONEY = 3;
    public static final int LENGTH_OF_SEPARATED_LINE = 4;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate dayFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dayTo = LocalDate.parse(dateTo, FORMATTER);
        String [] splittedLine = new String[LENGTH_OF_SEPARATED_LINE];
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                splittedLine = line.split(" ");
                LocalDate day = LocalDate.parse(splittedLine[INDEX_OF_DAY], FORMATTER);
                if (name.equals(splittedLine[INDEX_OF_NAME])
                        && (day.isAfter(dayFrom) || day.isEqual(dayFrom))
                        && (day.isBefore(dayTo) || day.isEqual(dayTo))) {
                    salary = salary + (Integer.parseInt(splittedLine[INDEX_OF_HOURS])
                            * Integer.parseInt(splittedLine[INDEX_OF_MONEY]));
                }
            }
            result.append(SEPARATOR + name + " - " + salary);
        }
        return result.toString();
    }
}
