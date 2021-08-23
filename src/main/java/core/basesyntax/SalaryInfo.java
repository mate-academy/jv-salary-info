package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int HOURS = 1;
    public static final int SALARY_PER_MONTH = 2;
    public static final int FOR_RECORD = 0;
    public static final int NAME = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder builder = new StringBuilder("Report for period "
                                                  + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] record = datum.split(" ");
                LocalDate changingDate = LocalDate.parse(record[FOR_RECORD], DATE_FORMAT);
                if (changingDate.isAfter(localDateFrom)
                        && (changingDate.isBefore(localDateTo)
                        || changingDate.isEqual(localDateTo))
                        && record[NAME].equals(name)) {
                    String[] hoursAndSalary = datum.substring(datum.indexOf(" ") + 1)
                                                                    .split(" ");
                    salary += Integer.parseInt(hoursAndSalary[HOURS])
                            * Integer.parseInt(hoursAndSalary[SALARY_PER_MONTH]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
