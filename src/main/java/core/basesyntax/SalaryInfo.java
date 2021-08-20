package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATA_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DAY = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int salaryCounter = 0;
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            for (String list : data) {
                String[] employeeList = list.split(" ");
                LocalDate checkDate = LocalDate.parse(employeeList[WORKING_DAY], DATA_FORMAT);
                LocalDate startDate = LocalDate.parse(dateFrom, DATA_FORMAT);
                LocalDate lastDate = LocalDate.parse(dateTo,DATA_FORMAT);
                if (name.equals(employeeList[EMPLOYEE_NAME]) && checkDate.isAfter(startDate)
                        || checkDate.isEqual(startDate)) {
                    if (checkDate.isBefore(lastDate) || checkDate.isEqual(lastDate)) {
                        salaryCounter += Integer.parseInt(employeeList[WORKING_HOURS])
                                * Integer.parseInt(employeeList[SALARY_PER_HOUR]);
                    }

                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryCounter);
            salaryCounter = 0;

        }
        return result.toString();
    }
}

