package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME = 3;
    private static final String REG_EX = " ";
    private static final String DASH = " - ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ").append(dateFrom)
                                                  .append(DASH).append(dateTo);

        LocalDate firstDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (String name : names) {
            int employeeSalary = 0;

            for (String datum : data) {
                String[] employeeInfo = datum.split(REG_EX);

                LocalDate employeeDate = LocalDate.parse(employeeInfo[DATE], DATE_FORMATTER);
                String employeeName = employeeInfo[NAME];
                int employeeHours = Integer.parseInt(employeeInfo[HOURS]);
                int employeeIncome = Integer.parseInt(employeeInfo[INCOME]);

                if (employeeName.equals(name) && !employeeDate.isBefore(firstDate)
                                              && !employeeDate.isAfter(lastDate)) {
                    employeeSalary += employeeHours * employeeIncome;
                }
            }
            result.append(LINE_SEPARATOR).append(name).append(DASH).append(employeeSalary);
        }
        return result.toString();
    }
}
