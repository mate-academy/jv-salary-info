package core.basesyntax;

import core.basesyntax.model.Employee;
import core.basesyntax.model.EmployeeWorkDayInfo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int INDEX_OF_WAGE = 3;
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<EmployeeWorkDayInfo> parsedData = parseData(data);
        List<EmployeeWorkDayInfo> filteredData = filterByDate(parsedData, dateFrom, dateTo);
        List<Employee> calculatedInfo = calculate(filteredData, names);
        return makeReport(calculatedInfo, names, dateFrom, dateTo);
    }

    public String makeReport(List<Employee> employees, String[] names,
                             String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        Arrays.stream(names).forEach(name -> {
            Employee employee = employees.stream()
                    .filter(person -> person.getName().equals(name))
                    .findFirst()
                    .get();
            reportBuilder.append(System.lineSeparator())
                    .append(employee.getName()).append(" - ")
                    .append(employee.getSalary());

        });
        return reportBuilder.toString();
    }

    public List<Employee> calculate(List<EmployeeWorkDayInfo> filteredData, String[] names) {
        List<Employee> employeesInfo = new ArrayList<>();
        Arrays.stream(names).forEach(name -> {
            Integer salary = filteredData.stream()
                    .filter(employeeWorkDayInfo ->
                            employeeWorkDayInfo.getEmployeeName().equals(name))
                    .map(employeeWorkDayInfo -> employeeWorkDayInfo.getHours()
                            * employeeWorkDayInfo.getIncomePerHour())
                    .reduce(0, Integer::sum);
            employeesInfo.add(new Employee(name, salary));
        });
        return employeesInfo;
    }

    public List<EmployeeWorkDayInfo> filterByDate(List<EmployeeWorkDayInfo> employeeWorkDayInfoList,
                                                  String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        return employeeWorkDayInfoList.stream()
                .filter(employeeWorkDayInfo ->
                        (!parsedDateFrom.isAfter(employeeWorkDayInfo.getDate()))
                        && !parsedDateTo.isBefore(employeeWorkDayInfo.getDate()))
                .collect(Collectors.toList());
    }

    public List<EmployeeWorkDayInfo> parseData(String[] data) {
        List<EmployeeWorkDayInfo> employeeWorkDayInfoList = new ArrayList<>();
        for (String line : data) {
            String[] splitLine = line.split(" ");
            employeeWorkDayInfoList.add(new EmployeeWorkDayInfo(splitLine[INDEX_OF_NAME],
                    LocalDate.parse(splitLine[INDEX_OF_DATE], DATE_FORMATTER),
                    Integer.parseInt(splitLine[INDEX_OF_HOUR]),
                    Integer.parseInt(splitLine[INDEX_OF_WAGE])));
        }
        return employeeWorkDayInfoList;
    }
}
