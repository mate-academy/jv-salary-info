package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = Arrays.stream(names).map(Employee::new).toArray(Employee[]::new);
        fillWorkingDays(employees, data);

        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (Employee employee : employees) {
            result.append("\n").append(employee.getName()).append(" - ")
                    .append(employee.getSalary(parseDate(dateFrom), parseDate(dateTo)));
        }

        return result.toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private WorkingDay parseWorkingDays(String data) {
        String[] splitFields = data.split(" ");
        WorkingDay day = new WorkingDay(parseDate(splitFields[0]));
        day.setHoursWorked(Integer.parseInt(splitFields[2]));
        day.setHourIncome(Integer.parseInt(splitFields[3]));
        return day;
    }

    private void fillWorkingDays(Employee[] employees, String[] data) {
        for (String dataLine : data) {
            String[] fields = dataLine.split(" ");
            for (Employee employee : employees) {
                if (employee.getName().equals(fields[1])) {
                    employee.addWorkingDay(parseWorkingDays(dataLine));
                }
            }
        }
    }
}
