package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final Map<String, Integer> employees = new HashMap<>();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        for (String name : names) {
            employees.put(name, 0);
        }
        StringBuilder reportForPeriod = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String employeeData : data) {
            String[] details = employeeData.split(" ");
            LocalDate date = getDate(details[0]);
            String nameOfEmployee = details[1];
            int dailySalary;
            if (date.compareTo(getDate(dateFrom)) >= 0 && date.compareTo(getDate(dateTo)) <= 0) {
                dailySalary = Integer.parseInt(details[2]) * Integer.parseInt(details[3]);
                employees.put(nameOfEmployee, employees.get(nameOfEmployee) + dailySalary);
            }

        }
        for (String name : names) {
            reportForPeriod.append(System.lineSeparator()).append(name)
                    .append(" - ").append(employees.get(name));
        }
        return reportForPeriod.toString();
    }

    private LocalDate getDate(String input) {
        return LocalDate.parse(input, formatter);
    }
}
