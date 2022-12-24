
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int FIRST_VALUE = 0;
    static final int SECOND_VALUE = 1;
    static final int THIRD_VALUE = 2;
    static final int FOUR_VALUE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromThisDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toThisDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            int employeeSalary = 0;
            for (String dates : data) {
                String[] split = dates.split(" ");
                LocalDate date = LocalDate.parse(split[FIRST_VALUE], FORMATTER);
                if (name.equals(split[SECOND_VALUE])
                        && (date.isEqual(fromThisDate) || date.isEqual(toThisDate)
                        || date.isAfter(fromThisDate) && date.isBefore(toThisDate))) {
                    int workedHours = Integer.parseInt(split[THIRD_VALUE]);
                    int payPerHour = Integer.parseInt(split[FOUR_VALUE]);
                    int result = workedHours * payPerHour;
                    employeeSalary += result;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(employeeSalary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}


