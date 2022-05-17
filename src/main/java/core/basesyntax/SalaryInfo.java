package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE = 0;
    public static final int NAME = 1;
    public static final int HOURS = 2;
    public static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dataStart = LocalDate.parse(dateFrom, FORMAT);
        LocalDate dataEnd = LocalDate.parse(dateTo, FORMAT);
        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }
        for (Employee employee: employees) {
            for (String dataString: data) {
                String[] dataStringArray = dataString.split(" ");
                if (employee.getName().equals(dataStringArray[NAME])
                        && checkDate(dataStringArray[DATE], dataStart, dataEnd)) {
                    int salary = getDaySalary(dataStringArray[HOURS], dataStringArray[SALARY]);
                    employee.addSalary(salary);
                }
            }
        }
        return employeesSalary(employees, dateFrom, dateTo);
    }

    private boolean checkDate(String date, LocalDate dateStart, LocalDate dateEnd) {
        LocalDate localDate = LocalDate.parse(date, FORMAT);
        return (localDate.isAfter(dateStart) || localDate.equals(dateStart))
                && (localDate.isBefore(dateEnd) || localDate.equals(dateEnd));
    }

    private int getDaySalary(String hours, String salaryPerHour) {
        return Integer.parseInt(hours) * Integer.parseInt(salaryPerHour);
    }

    private String employeesSalary(Employee[] employees, String dateStart, String dateEnd) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateStart)
                .append(" - ")
                .append(dateEnd);
        for (Employee employee: employees) {
            builder.append(System.lineSeparator())
                    .append(employee.getName())
                    .append(" - ")
                    .append(employee.getSalary());
        }
        return builder.toString();
    }
}
