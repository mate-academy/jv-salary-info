package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private int salary = 0;
    private LocalDate localDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {

            for (String info : data) {
                String[] employeeInfo = info.split(" ");

                if (name.equals(employeeInfo[1])
                        && strToDate(employeeInfo[0]).plusDays(1).isAfter(strToDate(dateFrom))
                        && strToDate(employeeInfo[0]).minusDays(1).isBefore(strToDate(dateTo))) {

                    salary += Integer.parseInt(employeeInfo[2]) * Integer.parseInt(employeeInfo[3]);
                }
            }

            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }

        return builder.toString();
    }

    private LocalDate strToDate(String inputDate) {
        try {
            localDate = LocalDate.parse(inputDate, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format");
        }

        return localDate;
    }
}
