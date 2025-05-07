package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_OF_WORKING_DAY = 0;
    private static final int WORKING_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;
    private static final int EMPLOYEE_NAME = 1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            result.append(System.lineSeparator()).append(name).append(" - ");
            for (String dataLine : data) {
                String[] employeeData = dataLine.split(" ");
                LocalDate dateOfWorkingDay
                        = LocalDate.parse(employeeData[DATE_OF_WORKING_DAY], FORMATTER);
                if (employeeData[EMPLOYEE_NAME].equals(name)
                        && !dateOfWorkingDay.isAfter(LocalDate.parse(dateTo, FORMATTER))
                        && !dateOfWorkingDay.isBefore(LocalDate.parse(dateFrom, FORMATTER))) {
                    totalSalary += Integer.parseInt(employeeData[WORKING_HOURS])
                            * Integer.parseInt(employeeData[SALARY_PER_HOUR]);
                }
            }
            result.append(totalSalary);
        }
        return result.toString();
    }
}
