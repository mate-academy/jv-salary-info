package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            builder.append(System.lineSeparator() + name + " - ");
            int salary = 0;
            for (String line : data) {
                if (line.matches("(.*)" + name + "(.*)")) {
                    String date = line.split(" ")[FIRST_ELEMENT];
                    LocalDate currentDate = LocalDate.parse(date, DATE_FORMATTER);
                    if (currentDate.isAfter(fromDate) && currentDate.isBefore(toDate)
                            || currentDate.isEqual(toDate)) {
                        String[] hoursAndPerHour
                                = line.split(name + " ")[SECOND_ELEMENT].split(" ");
                        int hours = Integer.parseInt(hoursAndPerHour[FIRST_ELEMENT]);
                        int perHour = Integer.parseInt(hoursAndPerHour[SECOND_ELEMENT]);
                        salary += hours * perHour;
                    }
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
