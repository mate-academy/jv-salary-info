package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;

            for (String info : data) {
                String[] employeeInfo = info.split(" ");

                if (name.equals(employeeInfo[1])
                        && LocalDate.parse(employeeInfo[0], DATE_TIME_FORMATTER).plusDays(1)
                            .isAfter(LocalDate.parse(dateFrom, DATE_TIME_FORMATTER))
                        && LocalDate.parse(employeeInfo[0], DATE_TIME_FORMATTER).minusDays(1)
                            .isBefore(LocalDate.parse(dateTo, DATE_TIME_FORMATTER))) {

                    salary += Integer.parseInt(employeeInfo[2]) * Integer.parseInt(employeeInfo[3]);
                }
            }

            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return builder.toString();
    }
}
