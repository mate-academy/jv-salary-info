package core.basesyntax;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class SalaryInfo extends SalaryCalculator {
    private final SalaryDataParser parser = new SalaryDataParser();
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ArrayList<Employee> employees;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        this.dateFrom = parser.parseDate(dateFrom);
        this.dateTo = parser.parseDate(dateTo);
        addEmployees(names);
        addData(parser.splitData(data), employees);
        sortSalaryData();
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (Employee employee : employees) {
            result.append(System.lineSeparator()).append(employee.getName())
                    .append(" - ")
                    .append(calculate(employee, this.dateFrom, this.dateTo));

        }
        System.out.println(result);
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
    public void addData(ArrayList<String[]> splittedData, ArrayList<Employee> employees) {
        String name;
        LocalDate date;
        int hoursPerDay;
        int dayIncome;
        for (String[] data : splittedData) {
            date = parser.parseDate(data[0]);
            hoursPerDay = Integer.parseInt(data[2]);
            dayIncome = Integer.parseInt(data[3]);
            name = data[1];
            getEmployee(name, employees)
                    .addDailySalary(new DailySalaryData(date, hoursPerDay, dayIncome));
        }
    }

    // TODO: 15.01.2023  Exception no such employee
    private Employee getEmployee(String name, ArrayList<Employee> employees) {
        for (Employee employee: employees){
            if (employee.getName().equals(name)){
                return employee;
            }
        }return null;
    }
}
