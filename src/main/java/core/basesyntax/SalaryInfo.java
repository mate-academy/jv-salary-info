package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getDateFromString(dateFrom);
        LocalDate localDateTo = getDateFromString(dateTo);
        EmployeeInfo[] employeeInfoArray = getInitializedEmployeeInfoArray(names);
        for (String row : data) {
            String[] infoParts = row.split(" ");
            String name = infoParts[NAME_INDEX];
            LocalDate date = getDateFromString(infoParts[DATE_INDEX]);
            int hours = Integer.parseInt(infoParts[HOURS_INDEX]);
            int salaryPerHour = Integer.parseInt(infoParts[SALARY_PER_HOUR_INDEX]);
            if (!dateInDiapason(date, localDateFrom, localDateTo)) {
                continue;
            }
            addSalaryToExistingUser(employeeInfoArray, name, hours, salaryPerHour);
        }
        return getResultInfoOfAllEmployee(employeeInfoArray, dateFrom, dateTo);
    }

    private EmployeeInfo[] getInitializedEmployeeInfoArray(String[] names) {
        EmployeeInfo[] employeeInfoArray = new EmployeeInfo[names.length];
        for (int i = 0; i < names.length; i++) {
            employeeInfoArray[i] = new EmployeeInfo(names[i]);
        }
        return employeeInfoArray;
    }

    private boolean dateInDiapason(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return date.isEqual(dateFrom)
                || date.isEqual(dateTo)
                || (date.isAfter(dateFrom) && date.isBefore(dateTo));
    }

    private String getResultInfoOfAllEmployee(EmployeeInfo[] employeeSalaryInfoArray,
                                              String dateFrom,
                                              String dateTo) {
        StringBuilder res = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (EmployeeInfo employeeSalaryInfo : employeeSalaryInfoArray) {
            res.append(System.lineSeparator()).append(employeeSalaryInfo);
        }
        return res.toString();
    }

    private void addSalaryToExistingUser(EmployeeInfo[] employeeSalaryInfoArray,
                                         String name,
                                         int hours,
                                         int salaryPerHour) {
        for (EmployeeInfo employeeSalaryInfo : employeeSalaryInfoArray) {
            if (employeeSalaryInfo != null && employeeSalaryInfo.getName().equals(name)) {
                employeeSalaryInfo.addSalary(hours, salaryPerHour);
            }
        }
    }

    private LocalDate getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }
}
