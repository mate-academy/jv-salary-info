package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_ELEMENT = 0;
    private static final int NAME_ELEMENT = 1;
    private static final int HOURS_ELEMENT = 2;
    private static final int INCOME_ELEMENT = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalArgumentException {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Empty array(s) or string(s)");
        }
        LocalDate firstDay = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate lastDay = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (String name : names) {
            int salary = calculateEmployeeSalary(data, name, firstDay, lastDay);
            report.append(name)
                    .append(" - ")
                    .append(salary)
                    .append("\n");
        }
        return report.substring(0, report.length() - 1);
    }

    public int calculateEmployeeSalary(String[] data, String employeeName, LocalDate firstDay,
                                       LocalDate lastDay) {
        int salary = 0;

        for (String info : data) {
            String[] employeeInfo = info.split(" ");
            LocalDate date = null;
            date = LocalDate.parse(employeeInfo[DATE_ELEMENT], DATE_FORMAT);

            if (date.isBefore(firstDay) || date.isAfter(lastDay)) {
                continue;
            }

            if (employeeInfo[NAME_ELEMENT].equals(employeeName)) {
                salary += Integer.parseInt(employeeInfo[HOURS_ELEMENT])
                        * Integer.parseInt(employeeInfo[INCOME_ELEMENT]);
            }
        }
        return salary;
    }
}
