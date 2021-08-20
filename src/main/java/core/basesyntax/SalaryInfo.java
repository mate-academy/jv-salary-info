package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromDateFormat = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToDateFormat = LocalDate.parse(dateTo, DATE_FORMAT).plusDays(1);
        StringBuilder builder = new StringBuilder("Report for period "
                                                  + dateFrom + " - " + dateTo);
        int salary = 0;
        for (String name : names) {
            for (String datum : data) {
                LocalDate changingDate = LocalDate.parse(
                        datum.substring(0, datum.indexOf(" ")), DATE_FORMAT);
                if (changingDate.isAfter(dateFromDateFormat)
                        && changingDate.isBefore(dateToDateFormat)) {
                    if (datum.contains(name)) {
                        String[] hoursAndSalary = datum.substring(datum.indexOf(" ") + 1)
                                                                        .split(" ");
                        salary += Integer.parseInt(hoursAndSalary[1])
                                * Integer.parseInt(hoursAndSalary[2]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
