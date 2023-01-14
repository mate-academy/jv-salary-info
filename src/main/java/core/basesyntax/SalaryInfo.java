package core.basesyntax;

import java.util.HashMap;

public class SalaryInfo extends SalaryCalculator {
    private CalendarDay dateFrom;
    private CalendarDay dateTo;
    private HashMap<String, Employee> employees;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo){
        SalaryDataParser dataParser = new SalaryDataParser(data);
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
        for (String name: names ) {
            result.append("\n").append(name)
                    .append(" - ")
                    .append(calculate(employees.get(name), this.dateFrom, this.dateTo));

        }
        System.out.println(result);
        return result.toString();
    }

    private void sortSalaryData() {
        SalaryDataSorter sorter = new SalaryDataSorter();
        for (String name: employees.keySet()){
            sorter.sortData(employees.get(name).getSalaryData());
        }
    }

    private void addEmployees(String[] names) {
        employees = new HashMap<>();
        for (String name : names){
            this.employees.put(name, new Employee(name));
        }
    }
}
