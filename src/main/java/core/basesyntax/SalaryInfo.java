package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DAY = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        int salaryCounter = 0;
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            for (String list : data) {
                String[] employeeList = list.split(" ");
                LocalDate checkDate = LocalDate.parse(employeeList[WORKING_DAY], DATE_FORMAT);
                LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
                LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
                if (name.equals(employeeList[EMPLOYEE_NAME]) && checkDate.isAfter(startDate)
                        || checkDate.isEqual(startDate)) {
                    if (checkDate.isBefore(endDate) || checkDate.isEqual(endDate)) {
                        salaryCounter += Integer.parseInt(employeeList[WORKING_HOURS])
                                * Integer.parseInt(employeeList[SALARY_PER_HOUR]);
                    }

                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryCounter);
            salaryCounter = 0;

        }
        return report.toString();
    }
}
