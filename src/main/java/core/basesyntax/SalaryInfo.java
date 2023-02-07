package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DAY = 0;
    public static final int NAME = 1;
    public static final int HOUR = 2;
    public static final int MONEY = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String enter = System.lineSeparator();
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate dayFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate dayTo = LocalDate.parse(dateTo,formatter);
        LocalDate day;
        String [] lineArray = new String[4];
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                lineArray = line.split(" ");
                day = LocalDate.parse(lineArray[DAY],formatter);
                if (name.equals(lineArray[NAME]) && (day.isAfter(dayFrom) || day.isEqual(dayFrom))
                        && (day.isBefore(dayTo) || day.isEqual(dayTo))) {
                    salary = salary + (Integer.parseInt(lineArray[HOUR])
                            * Integer.parseInt(lineArray[MONEY]));
                }
            }
            result.append(enter + name + " - " + salary);
        }
        return result.toString();
    }
}
