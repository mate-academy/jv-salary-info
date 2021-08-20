package core.basesyntax;

import core.basesyntax.core.Employee;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SalaryInfo {
    private static final int BEGIN_INDEX_OF_DATA = 0;
    private static final int END_INDEX_OF_DATA = 10;
    private static final String REGULAR_EXPRESSION_FOR_EMPLOYEE_INCOME_PER_HOUR = ".+(\\d+) ";
    private static final String REGULAR_EXPRESSION_FOR_EMPLOYEE_NAME = "\\W|\\d";
    private static final String REGULAR_EXPRESSION_FOR_EMPLOYEE_WORKING_HOURS
            = ".+[a-zA-Z] | (\\d+)";
    private static final int DAY_START_INDEX = 0;
    private static final int DAY_END_INDEX = 2;
    private static final int MONTH_START_INDEX = 3;
    private static final int MONTH_END_INDEX = 5;
    private static final int YEAR_START_INDEX = 6;
    private LocalDate fromDate;
    private LocalDate toDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        initializingDate(dateFrom, dateTo);
        LinkedList<Employee> linkedListOfEmployee = formLinkedListOfEmployee(names);
        for (String singleData : data) {
            String nameOfCurrentEmploy = singleData
                    .replaceAll(REGULAR_EXPRESSION_FOR_EMPLOYEE_NAME, "");
            if (validDataCheck(singleData.substring(BEGIN_INDEX_OF_DATA, END_INDEX_OF_DATA))
                    && linkedListOfEmployee.contains(new Employee(nameOfCurrentEmploy))) {
                Employee currentEmploy
                        = getEmployeeFromList(linkedListOfEmployee, nameOfCurrentEmploy);
                long hours = Long.parseLong(singleData
                        .replaceAll(REGULAR_EXPRESSION_FOR_EMPLOYEE_WORKING_HOURS, ""));
                long incomePerHour = Long.parseLong(singleData
                        .replaceAll(REGULAR_EXPRESSION_FOR_EMPLOYEE_INCOME_PER_HOUR, ""));
                currentEmploy.setCash(currentEmploy.getCash() + (hours * incomePerHour));
            }
        }
        return formOutPutString(linkedListOfEmployee, dateFrom, dateTo);
    }

    private Employee getEmployeeFromList(List<Employee> linkedListOfEmployee,
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

    private boolean validDataCheck(String s) {
        LocalDate currentDate = LocalDate.of(Integer.parseInt(s.substring(YEAR_START_INDEX)),
                Integer.parseInt(s.substring(MONTH_START_INDEX, MONTH_END_INDEX)),
                Integer.parseInt(s.substring(DAY_START_INDEX, DAY_END_INDEX)));
        return (currentDate.compareTo(fromDate) >= 0
                && currentDate.compareTo(toDate) <= 0);
    }

    private void initializingDate(String dateFrom, String dateTo) {
        fromDate = LocalDate.of(Integer.parseInt(dateFrom.substring(YEAR_START_INDEX)),
                Integer.parseInt(dateFrom.substring(MONTH_START_INDEX, MONTH_END_INDEX)),
                Integer.parseInt(dateFrom.substring(DAY_START_INDEX, DAY_END_INDEX)));
        toDate = LocalDate.of(Integer.parseInt(dateTo.substring(YEAR_START_INDEX)),
                Integer.parseInt(dateTo.substring(MONTH_START_INDEX, MONTH_END_INDEX)),
                Integer.parseInt(dateTo.substring(DAY_START_INDEX, DAY_END_INDEX)));
    }

    private LinkedList<Employee> formLinkedListOfEmployee(String[] names) {
        LinkedList<Employee> linkedList = new LinkedList<>();
        for (String name : names) {
            linkedList.add(new Employee(name));
        }
        return linkedList;
    }
}
