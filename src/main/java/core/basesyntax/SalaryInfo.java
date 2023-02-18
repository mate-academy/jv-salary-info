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
    private static final int PER_HOUR_INDEX = 3;

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
                String perHourOnLine = splited[PER_HOUR_INDEX];
                LocalDate currentDate = LocalDate.parse(dateOnLine, DATE_FORMATTER);
                if ((currentDate.isAfter(fromDate) && currentDate.isBefore(toDate)
                        || currentDate.isEqual(toDate)) && nameOnLine.equals(name)) {
                    int hours = Integer.parseInt(hoursOnLine);
                    int perHour = Integer.parseInt(perHourOnLine);
                    salary += hours * perHour;
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
