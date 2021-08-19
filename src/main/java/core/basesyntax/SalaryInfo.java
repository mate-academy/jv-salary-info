package core.basesyntax;

import core.basesyntax.core.Date;
import core.basesyntax.core.Employee;

import java.util.LinkedList;

public class SalaryInfo {

    private static final int BEGIN_INDEX_OF_DATA = 0;
    private static final int END_INDEX_OF_DATA = 10;
    private static final String REGULAR_EXPRESSION_FOR_EMPLOYEE_INCOME_PER_HOUR = ".+(\\d+) ";
    private static final String REGULAR_EXPRESSION_FOR_EMPLOYEE_NAME = "\\W|\\d";
    private static final String REGULAR_EXPRESSION_FOR_EMPLOYEE_WORKING_HOURS
            = ".+[a-zA-Z] | (\\d+)";
    private Date fromData;
    private Date toDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        initializingDate(dateFrom, dateTo);
        LinkedList<Employee> linkedListOfEmployee = formLinkedListOfEmployee(names);
        for (String singleData : data) {
            String nameOfCurrentEmploy = singleData
                    .replaceAll(REGULAR_EXPRESSION_FOR_EMPLOYEE_NAME, "");
            if (validDataCheck(singleData.substring(BEGIN_INDEX_OF_DATA, END_INDEX_OF_DATA))
                    && isEmployeeInLinkedList(linkedListOfEmployee, nameOfCurrentEmploy)) {
                Employee currentEmploy
                        = getEmployeeInLinkedList(linkedListOfEmployee, nameOfCurrentEmploy);
                long hours = Long.parseLong(singleData
                        .replaceAll(REGULAR_EXPRESSION_FOR_EMPLOYEE_WORKING_HOURS, ""));
                long incomePerHour = Long.parseLong(singleData
                        .replaceAll(REGULAR_EXPRESSION_FOR_EMPLOYEE_INCOME_PER_HOUR, ""));
                currentEmploy.setCash(currentEmploy.getCash() + (hours * incomePerHour));
            }
        }
        return formOutPutString(linkedListOfEmployee, fromData.toString(), toDate.toString());
    }

    private Employee getEmployeeInLinkedList(LinkedList<Employee> linkedListOfEmployee,
                                             String nameOfEmployee) {
        for (Employee employee : linkedListOfEmployee) {
            if (employee.getName().equals(nameOfEmployee)) {
                return employee;
            }
        }
        return null;
    }

    private String formOutPutString(LinkedList<Employee> employeeLinkedList,
                                    String fromDate, String toDate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ");
        stringBuilder.append(fromDate);
        stringBuilder.append(" - ");
        stringBuilder.append(toDate);
        stringBuilder.append(System.lineSeparator());
        for (Employee currentEmployee : employeeLinkedList) {
            stringBuilder.append(currentEmployee.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    private boolean isEmployeeInLinkedList(LinkedList<Employee> linkedListOfEmployee,
                                           String nameOfEmployee) {
        for (Employee employee : linkedListOfEmployee) {
            if (employee.getName().equals(nameOfEmployee)) {
                return true;
            }
        }
        return false;
    }

    private boolean validDataCheck(String s) {
        Date currentDate = new Date(s);
        long longFromDate = fromData.getAsIntegerForEquals();
        long longToDate = toDate.getAsIntegerForEquals();
        long longCurrentDate = currentDate.getAsIntegerForEquals();
        return longFromDate <= longCurrentDate && longCurrentDate <= longToDate;
    }

    private void initializingDate(String dateFrom, String dateTo) {
        this.fromData = new Date(dateFrom);
        this.toDate = new Date(dateTo);
    }

    private LinkedList<Employee> formLinkedListOfEmployee(String[] names) {
        LinkedList<Employee> linkedList = new LinkedList<>();
        for (String name : names) {
            linkedList.add(new Employee(name));
        }
        return linkedList;
    }
}
