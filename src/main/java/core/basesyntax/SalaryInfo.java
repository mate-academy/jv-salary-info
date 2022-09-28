package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_FOR_PERIOD = "Report for period ";
    private static final String DATE_TIME_SEPARATOR = " - ";
    private static final int DATE_OF_START_WORK = 0;
    private static final int WORKER_NAME = 1;
    private static final int WORKDAY = 2;
    private static final int WORKING_HOURS = 3;

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
            LocalDate parsedDate = LocalDate.parse(info[DATE_OF_START_WORK], DATE_TIME_FORMAT);
            employee.setDayOfWorkStart(parsedDate);
            employee.setName(info[WORKER_NAME]);
            employee.setNumberOfWorkingDays(Integer.parseInt(info[WORKDAY]));
            employee.setWorkingHours(Integer.parseInt(info[WORKING_HOURS]));
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
