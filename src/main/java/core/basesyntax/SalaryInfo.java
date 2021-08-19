package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getDateFromString(dateFrom);
        LocalDate localDateTo = getDateFromString(dateTo);
        EmployeeInfo[] employeeInfoArray = getInitializedEmployeeInfoArray(names);
        for (String dataForOneEmployee : data) {
            String[] infoParts = dataForOneEmployee.split(" ");
            String name = infoParts[1];
            LocalDate date = getDateFromString(infoParts[0]);
            int hours = Integer.parseInt(infoParts[2]);
            int salaryPerHour = Integer.parseInt(infoParts[3]);
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

    private String getResultInfoOfAllEmployee(EmployeeInfo[] employeeArrayInfo,
                                              String dateFrom,
                                              String dateTo) {
        StringBuilder res = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (EmployeeInfo employeeInfo : employeeArrayInfo) {
            res.append(System.lineSeparator()).append(employeeInfo);
        }
        return res.toString();
    }

    private void addSalaryToExistingUser(EmployeeInfo[] employeeArrayInfo,
                                         String name,
                                         int hours,
                                         int salaryPerHour) {
        for (EmployeeInfo employeeInfo : employeeArrayInfo) {
            if (employeeInfo != null && employeeInfo.getName().equals(name)) {
                employeeInfo.addSalary(hours, salaryPerHour);
            }
        }
    }

    private LocalDate getDateFromString(String date) {
        String[] dateParts = date.split("\\.");
        return LocalDate.of(Integer.parseInt(dateParts[2]),
                Integer.parseInt(dateParts[1]),
                Integer.parseInt(dateParts[0]));
    }
}
