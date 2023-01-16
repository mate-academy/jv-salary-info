package core.basesyntax;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class SalaryInfo extends SalaryCalculator {
    private final SalaryDataParser parser = new SalaryDataParser();
    private ArrayList<Employee> employees;
    private LocalDate dayTo;
    private LocalDate dayFrom;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        dayFrom = parser.parseDate(dateFrom);
        dayTo = parser.parseDate(dateTo);
        ArrayList<String[]> splittedData = parser.splitData(data);
        addEmployees(names);
        try {
            addData(splittedData, employees);
        } catch (NoSuchEmployeeException e) {
            System.err.println("Can't add a salary data for the employee");
        }
        sortSalaryData();
        return infoToString(dateFrom, dateTo);
    }

    private String infoToString(String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int totalEarnings = 0;
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (Employee employee : employees) {
            totalEarnings = calculate(employee, dayFrom, dayTo);
            result.append(System.lineSeparator()).append(employee.getName())
                    .append(" - ")
                    .append(totalEarnings);
        }
        return result.toString();
    }

    private void sortSalaryData() {
        for (Employee employee : employees) {
            Collections.sort(employee.getSalaryData());
        }
    }

    private void addEmployees(String[] names) {
        employees = new ArrayList<>();
        for (String name : names) {
            this.employees.add(new Employee(name));
        }
    }

    public void addData(ArrayList<String[]> splittedData, ArrayList<Employee> employees)
            throws NoSuchEmployeeException {
        String name;
        LocalDate date;
        Employee employee;
        int hoursPerDay;
        int dayIncome;
        for (String[] data : splittedData) {
            date = parser.parseDate(data[0]);
            hoursPerDay = Integer.parseInt(data[2]);
            dayIncome = Integer.parseInt(data[3]);
            name = data[1];
            employee = getEmployee(name, employees);
            if (employee == null) {
                throw new NoSuchEmployeeException(name);
            }
            employee.addDailySalary(new DailySalaryData(date, hoursPerDay, dayIncome));
        }
    }

    private Employee getEmployee(String name, ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }
}
