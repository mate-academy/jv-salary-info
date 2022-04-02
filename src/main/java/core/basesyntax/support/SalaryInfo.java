package core.basesyntax.support;

import core.basesyntax.model.Employee;
import java.util.Date;

public class SalaryInfo {
    private EmployeeSupport employeeSupport;
    private Employee[] employees;

    public SalaryInfo() {
        this.employeeSupport = new EmployeeSupport();
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        employees = employeeSupport.calculateSalaryForPeriod(names, data, dateFrom, dateTo);
        return report(employees, dateFrom, dateTo);
    }

    private String report(Employee[] employees, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < employees.length; i++) {
            stringBuilder.append(System.lineSeparator())
                    .append(employees[i].getName())
                    .append(" - ")
                    .append(employees[i].getMoneyEarnedForCertainPeriod());
        }
        return stringBuilder.toString();
    }

    private static boolean isWithinPeriod(Date testDate, Date startDate, Date endDate) {
        return testDate.getTime() >= startDate.getTime()
                && testDate.getTime() <= endDate.getTime();
    }
}
