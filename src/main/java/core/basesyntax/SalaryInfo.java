package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.model.EmployeeWorkDayInfo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class SalaryInfo {
    private static final int INDEX_OF_DATES = 0;
    private static final int INDEX_OF_NAMES = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_WAGES = 3;
    private static final DateTimeFormatter TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<EmployeeWorkDayInfo> employeeWorkDayInfoList = parseData(data);
        filterByDate(employeeWorkDayInfoList, dateFrom, dateTo);
        Queue<String> statistic = calculate(names);
        Storage.getEmployeeWorkDayInfo().clear();
        return makeReport(statistic, names, dateFrom, dateTo);
    }

    public String makeReport(Queue<String> statistic, String[] names,
                             String dateFrom, String dateTo) {
        String header = "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator();
        StringBuilder builder = new StringBuilder(header);
        Arrays.stream(names).forEach(name -> {
            builder.append(statistic.poll());
            if (statistic.peek() != null) {
                builder.append(System.lineSeparator());
            }
        });
        return builder.toString();
    }

    public Queue<String> calculate(String[] names) {
        Queue<String> salaryStatistic = new LinkedList<>();
        Arrays.stream(names).forEach(name -> {
            Integer salary = Storage.getEmployeeWorkDayInfo().stream()
                    .filter(employeeWorkDayInfo ->
                            employeeWorkDayInfo.getEmployeeName().equals(name))
                    .map(employeeWorkDayInfo -> employeeWorkDayInfo.getHours()
                            * employeeWorkDayInfo.getIncomePerHour())
                    .reduce(0, Integer::sum);
            String employeeSalary = name + " - " + salary;
            salaryStatistic.add(employeeSalary);
        });
        return salaryStatistic;
    }

    public void filterByDate(List<EmployeeWorkDayInfo> employeeWorkDayInfoList,
                                                  String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, TIME_FORMATTER);
        List<EmployeeWorkDayInfo> filteredInfo = employeeWorkDayInfoList.stream()
                .filter(employeeWorkDayInfo ->
                        (!parsedDateFrom.isAfter(employeeWorkDayInfo.getDate()))
                        && !parsedDateTo.isBefore(employeeWorkDayInfo.getDate()))
                .collect(Collectors.toList());
        Storage.getEmployeeWorkDayInfo().addAll(filteredInfo);
    }

    public List<EmployeeWorkDayInfo> parseData(String[] data) {
        List<EmployeeWorkDayInfo> employeeWorkDayInfoList = new ArrayList<>();
        for (String datum : data) {
            String[] split = datum.split(" ");
            employeeWorkDayInfoList.add(new EmployeeWorkDayInfo(split[INDEX_OF_NAMES],
                    LocalDate.parse(split[INDEX_OF_DATES], TIME_FORMATTER),
                    Integer.parseInt(split[INDEX_OF_HOURS]),
                    Integer.parseInt(split[INDEX_OF_WAGES])));
        }
        return employeeWorkDayInfoList;
    }
}
