
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromThisDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toThisDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            String testString = "";
            int employeeSalary = 0;
            for (String dates : data) {
                String[] split = dates.split(" ");
                LocalDate date = LocalDate.parse(split[0], FORMATTER);
                if (date.isEqual(fromThisDate) || date.isEqual(toThisDate)
                        || date.isAfter(fromThisDate) && date.isBefore(toThisDate)) {
                    if (name.equals(split[1])) {
                        int workedHours = Integer.parseInt(split[2]);
                        int payPerHour = Integer.parseInt(split[3]);
                        int result = workedHours * payPerHour;
                        employeeSalary += result;
                        if (!testString.equals(name)) {
                            testString += name;
                            builder.append(System.lineSeparator())
                                    .append(name)
                                    .append(" - ");
                        }
                    }
                } else {
                    if (!testString.equals(name)) {
                        testString += name;
                        builder.append(System.lineSeparator())
                                .append(name)
                                .append(" - ");
                    }
                }
            }
            builder.append(employeeSalary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}


