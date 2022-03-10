package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<Employee> dataToEmployeeList = parseDataToEmployeeList(data);
        LocalDate dateF = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateT = LocalDate.parse(dateTo, dateTimeFormatter);
        HashMap<String, Integer> map = new HashMap<>();

        for (Employee employee : dataToEmployeeList) {
            if (employee.getDate().compareTo(dateF) >= 0
                    && employee.getDate().compareTo(dateT) <= 0) {
                for (String name : names) {
                    if (employee.getName().equals(name)) {
                        int salary = employee.getWorkingHour() * employee.getSalary();
                        if (map.containsKey(name)) {
                            map.put(name, map.get(name) + salary);
                        } else {
                            map.put(name, salary);
                        }
                    }
                }
            }
        }
        return getFormater(map, dateFrom, dateTo);
    }

    private String getFormater(HashMap<String, Integer> hashMap, String dateFrom, String dateTo) {
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
        for (String elem : data) {
            employees.add(getEmployee(elem));
        }
        return employees;
    }

    private Employee getEmployee(String elem) {
        Employee employee = new Employee();
        String[] emp = elem.split(" ");
        employee.setDate(LocalDate.parse(emp[0], dateTimeFormatter));
        employee.setName(emp[1]);
        employee.setWorkingHour(Integer.parseInt(emp[2]));
        employee.setSalary(Integer.parseInt(emp[3]));
        return employee;
    }
}
