package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int INDEX_OF_DAY = 0;
    public static final int INDEX_OF_NAME = 1;
    public static final int INDEX_OF_HOURS = 2;
    public static final int INDEX_OF_MONEY = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String enter = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate dayFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate dayTo = LocalDate.parse(dateTo,formatter);
        String [] lineArray = new String[4];
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                lineArray = line.split(" ");
                LocalDate day = LocalDate.parse(lineArray[INDEX_OF_DAY],formatter);
                if (name.equals(lineArray[INDEX_OF_NAME])
                        && (day.isAfter(dayFrom) || day.isEqual(dayFrom))
                        && (day.isBefore(dayTo) || day.isEqual(dayTo))) {
                    salary = salary + (Integer.parseInt(lineArray[INDEX_OF_HOURS])
                            * Integer.parseInt(lineArray[INDEX_OF_MONEY]));
                }
            }
            result.append(enter + name + " - " + salary);
        }
        return result.toString();
    }
}
