package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                if (employee.getName().equals(dataStringArray[1])
                        && checkDate(dataStringArray[0], dataStart, dataEnd)) {
                    int salary = getDaySalary(dataStringArray[2], dataStringArray[3]);
                    employee.addSalary(salary);
                }
            }
        }
        return employeesSalary(employees, dateFrom, dateTo);
    }

    public boolean checkDate(String date, LocalDate dateStart, LocalDate dateEnd) {
        LocalDate localDate = LocalDate.parse(date, FORMAT);
        return (localDate.isAfter(dateStart) || localDate.equals(dateStart))
                && (localDate.isBefore(dateEnd) || localDate.equals(dateEnd));
    }

    public int getDaySalary(String hours, String salaryPerHour) {
        return Integer.parseInt(hours) * Integer.parseInt(salaryPerHour);
    }

    public String employeesSalary(Employee[] employees, String dateStart, String dateEnd) {
        StringBuilder builder = new StringBuilder();
        builder.append(" Report for period ")
                .append(dateStart)
                .append(" - ")
                .append(dateEnd)
                .append(System.lineSeparator());
        for (Employee employee: employees) { // John - 900
            builder.append(employee.getName())
                    .append(" - ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
