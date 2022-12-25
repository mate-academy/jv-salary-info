
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_INDEX = 2;
    static final int INCOME_PER_HOURS_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromThisDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toThisDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int employeeSalary = 0;
            for (String line : data) {
                String[] split = line.split(" ");
                LocalDate date = LocalDate.parse(split[DATE_INDEX], FORMATTER);
                if (name.equals(split[NAME_INDEX])
                        && (date.isEqual(fromThisDate) || date.isEqual(toThisDate)
                        || date.isAfter(fromThisDate) && date.isBefore(toThisDate))) {
                    int workedHours = Integer.parseInt(split[HOURS_INDEX]);
                    int payPerHour = Integer.parseInt(split[INCOME_PER_HOURS_INDEX]);
                    int result = workedHours * payPerHour;
                    employeeSalary += result;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(employeeSalary);
        }
        return builder.toString();
    }
}


