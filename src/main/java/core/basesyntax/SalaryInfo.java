package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<Employee> dataToEmployeeList = parseDataToEmployeeList(data);
        LocalDate dateF = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateT = LocalDate.parse(dateTo, dateTimeFormatter);
        Map<String, Integer> map = new LinkedHashMap<>();

        for (String name : names) {
            map.put(name, 0);
        }
        for (Employee employee : dataToEmployeeList) {
            if (employee.getDate().compareTo(dateF) >= 0
                    && employee.getDate().compareTo(dateT) <= 0) {
                if (map.containsKey(employee.getName())) {
                    map.put(employee.getName(), map.get(employee.getName())
                                                + employee.getWorkingHour() * employee.getSalary());
                } else {
                    map.put(employee.getName(), employee.getWorkingHour() * employee.getSalary());
                }
            }
        }
        return getReport(map, dateFrom, dateTo);
    }

    private String getReport(Map<String, Integer> hashMap, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (Map.Entry entry : hashMap.entrySet()) {
            stringBuilder
                    .append(System.lineSeparator())
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    private List<Employee> parseDataToEmployeeList(String[] data) {
        List<Employee> employees = new ArrayList<>();
        for (String element : data) {
            employees.add(getEmployee(element));
        }
        return employees;
    }

    private Employee getEmployee(String element) {
        Employee employee = new Employee();
        String[] employeeArray = element.split(" ");
        employee.setDate(LocalDate.parse(employeeArray[0], dateTimeFormatter));
        employee.setName(employeeArray[1]);
        employee.setWorkingHour(Integer.parseInt(employeeArray[2]));
        employee.setSalary(Integer.parseInt(employeeArray[3]));
        return employee;
    }
}
