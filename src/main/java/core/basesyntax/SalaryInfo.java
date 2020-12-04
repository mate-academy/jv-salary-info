package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = Arrays.stream(names).map(Employee::new).toArray(Employee[]::new);
        Company.fillWorkingDays(employees, data);

        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (Employee employee : employees) {
            result.append("\n").append(employee.getName()).append(" - ")
                    .append(employee.getSalary(Company.parseDate(dateFrom), Company.parseDate(dateTo)));
        }

        return result.toString();
    }
}
