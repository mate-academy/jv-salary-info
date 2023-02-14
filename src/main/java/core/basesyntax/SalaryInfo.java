package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            Employee employee = new Employee(names[i]);
            for (int j = 0; j < data.length; j++) {
                if (names[i].contains(extractName(data[j]))) {
                    if (containDate(extractDate(data[j]), dateFrom, dateTo)) {
                        employee.addSalary(extractSalary(data[j]), extractHour(data[j]));
                    }
                }
            }
            employees[i] = employee;
        }
        return printData(employees, dateFrom, dateTo);
    }

    private boolean containDate(String date, String dateFrom, String dateTo) {
        LocalDate dateFrom1 = convertStringToDate(dateFrom);
        LocalDate dateTo1 = convertStringToDate(dateTo);
        LocalDate date1 = convertStringToDate(date);
        if (!date1.isBefore(dateFrom1) && !date1.isAfter(dateTo1)) {
            return true;
        } else {
            return false;
        }
    }

    private LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private String extractDate(String data) {
        String[] strings = data.split(" ");
        return strings[INDEX_DATE];
    }

    private String extractName(String data) {
        String[] strings = data.split(" ");
        return strings[INDEX_NAME];
    }

    private int extractSalary(String data) {
        String[] strings = data.split(" ");
        return Integer.parseInt(strings[INDEX_SALARY]);
    }

    private int extractHour(String data) {
        String[] strings = data.split(" ");
        return Integer.parseInt(strings[INDEX_HOUR]);
    }

    private String printData(Employee[] employee, String dateFrom, String dateTo) {
        String string = "Report for period " + dateFrom + " - " + dateTo + "\n";
        if (employee.length == 0) {
            return string;
        }
        for (int i = 0; i < employee.length - 1; i++) {
            string += (employee[i].getName() + " - " + employee[i].getSalary() + "\n");
        }
        string += (employee[employee.length - 1].getName() + " - "
                + employee[employee.length - 1].getSalary());
        return string;
    }
}
