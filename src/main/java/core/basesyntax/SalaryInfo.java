package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int RATE = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String employee : data) {
                String[] employeeData = employee.split(" ");
                if (name.equals(employeeData[NAME]) && isInPeriod(employeeData[DATE], from, to)) {
                    salary += Integer.parseInt(employeeData[WORK_HOURS])
                            * Integer.parseInt(employeeData[RATE]);
                }
            }
            salaryInfo.append('\n').append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }

    private boolean isInPeriod(String date, LocalDate from, LocalDate to) {
        LocalDate workingDay = LocalDate.parse(date, formatter);
        return (workingDay.isEqual(from) || workingDay.isAfter(from))
                && (workingDay.isBefore(to) || workingDay.isEqual(to));
    }
}
