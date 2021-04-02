package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATA_INDEX = 0;
    private static final int EMPLOYEE_HOURS = 2;
    private static final int EMPLOYEE_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");

        LocalDate checkDateFrom = convertStringToDate(dateFrom);
        LocalDate checkDateTo = convertStringToDate(dateTo);

        for (String name : names) {
            int employeeSalary = 0;
            for (String dates : data) {
                if (dates.contains(name)) {
                    String[] employeeInfo = dates.split(" ");
                    if (convertStringToDate(employeeInfo[DATA_INDEX]).isAfter(checkDateFrom)
                            && convertStringToDate(employeeInfo[DATA_INDEX])
                            .isBefore(checkDateTo.plusDays(1))) {
                        employeeSalary += Integer.parseInt(employeeInfo[EMPLOYEE_HOURS])
                                * Integer.parseInt(employeeInfo[EMPLOYEE_SALARY_PER_HOUR]);
                    }
                }
            }
            stringBuilder.append(name + " - " + employeeSalary + "\n");
        }
        return stringBuilder.toString().trim();
    }

    public LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date, DATA_FORMATTER);
    }
}
