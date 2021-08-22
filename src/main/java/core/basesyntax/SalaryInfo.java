package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int FOR_GET_SALARY = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder builder = new StringBuilder("Report for period "
                                                  + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                LocalDate changingDate = LocalDate.parse(
                        datum.substring(0, datum.indexOf(" ")), DATE_FORMAT);
                if (changingDate.compareTo(localDateFrom) >= 0
                        && changingDate.compareTo(localDateTo) <= 0
                        && datum.contains(name)) {
                    String[] hoursAndSalary = datum.substring(datum.indexOf(" ") + 1)
                                                                    .split(" ");
                    salary += Integer.parseInt(hoursAndSalary[FOR_GET_SALARY])
                            * Integer.parseInt(hoursAndSalary[FOR_GET_SALARY + 1]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
