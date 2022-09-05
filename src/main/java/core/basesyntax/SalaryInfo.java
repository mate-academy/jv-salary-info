package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_FOR_PERIOD = "Report for period ";
    private static final String DATE_TIME_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMAT);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REPORT_FOR_PERIOD)
                .append(dateFrom)
                .append(DATE_TIME_SEPARATOR)
                .append(dateTo);
        Employee[] employees = new Employee[data.length];
        for (int i = 0; i < data.length; i++) {
            Employee employee = new Employee();
            String[] info = data[i].split(" ");
            LocalDate parsedDate = LocalDate.parse(info[0], DATE_TIME_FORMAT);
            employee.setDayOfWorkStart(parsedDate);
            employee.setName(info[1]);
            employee.setNumberOfWorkingDays(Integer.parseInt(info[2]));
            employee.setWorkingHours(Integer.parseInt(info[3]));
            employees[i] = employee;
        }

        for (String name : names) {
            int salary = 0;
            for (Employee employee : employees) {
                if (employee.getName().equals(name)
                        && employee.getDayOfWorkStart().compareTo(from) >= 0
                        && employee.getDayOfWorkStart().compareTo(to) <= 0) {
                    int salaryForDay = employee.getNumberOfWorkingDays()
                            * employee.getWorkingHours();
                    salary = salary + salaryForDay;
                }
            }
            stringBuilder.append(System
                            .lineSeparator())
                    .append(name)
                    .append(DATE_TIME_SEPARATOR)
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
