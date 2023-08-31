package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String HYPHEN = " - ";
    private static final String SPACE = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int TIME = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PER_HOURS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom,DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo,DATE_TIME_FORMATTER);
        stringBuilder.append("Report for period " + dateFrom + HYPHEN + dateTo);
        int salary = 0;
        for (String name : names) {
            for (String dateString : data) {
                String[] dateSplit = dateString.split(SPACE);
                String time = dateSplit[TIME];
                String nameFromString = dateSplit[NAME];
                int hours = Integer.parseInt(dateSplit[HOURS]);
                int perHours = Integer.parseInt(dateSplit[PER_HOURS]);
                LocalDate localTime = LocalDate.parse(time,DATE_TIME_FORMATTER);
                if (nameFromString.equals(name)
                        && localTime.isAfter(localDateFrom.minusDays(1))
                        && localTime.isBefore(localDateTo.plusDays(1))) {
                    salary += hours * perHours;
                }
            }
            stringBuilder.append(LINE_SEPARATOR + name + HYPHEN + salary);
            salary = 0;
        }
        return stringBuilder.toString();
    }
}
