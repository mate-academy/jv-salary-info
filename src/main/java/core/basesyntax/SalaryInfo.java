package core.basesyntax;

import java.util.ArrayList;

public class SalaryInfo extends SalaryCalculator {
    private CalendarDay dateFrom;
    private CalendarDay dateTo;
    private ArrayList<Employee> employees;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SalaryDataParser dataParser = new SalaryDataParser();
        dataParser.parseData(data);
        addEmployees(names);
        dataParser.addData(employees);
        this.dateFrom = dataParser.arrayToCalendarDay(dataParser.parseDate(dateFrom));
        this.dateTo = dataParser.arrayToCalendarDay(dataParser.parseDate(dateTo));
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
        SalaryDataSorter sorter = new SalaryDataSorter();
        for (Employee employee : employees) {
            sorter.sortData(employee.getSalaryData());
        }
    }

    private void addEmployees(String[] names) {
        employees = new ArrayList<>();
        for (String name : names) {
            this.employees.add(new Employee(name));
        }
    }
}
