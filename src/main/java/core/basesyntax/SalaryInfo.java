package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_SALARY = 3;
    private static final String DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = new Employee[names.length];
        LocalDate from = convertStringToDate(dateFrom);
        LocalDate to = convertStringToDate(dateTo);
        for (int i = 0; i < names.length; i++) {
            Employee employee = new Employee(names[i]);
            for (int j = 0; j < data.length; j++) {
                String[] cutData = data[j].split(DELIMITER);
                if (names[i].contains(extractName(cutData))) {
                    LocalDate workday = convertStringToDate(extractDate(cutData));
                    if (containDate(workday, from, to)) {
                        employee.addSalary(extractSalary(cutData), extractHour(cutData));
                    }
                }
            }
            employees[i] = employee;
        }
        return reportData(employees, dateFrom, dateTo);
    }

    private boolean containDate(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }

    private LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private String extractDate(String[] data) {
        return data[INDEX_DATE];
    }

    private String extractName(String[] data) {
        return data[INDEX_NAME];
    }

    private int extractSalary(String[] data) {
        return Integer.parseInt(data[INDEX_SALARY]);
    }

    private int extractHour(String[] data) {
        return Integer.parseInt(data[INDEX_HOUR]);
    }

    private String reportData(Employee[] employee, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        if (employee.length == 0) {
            return stringBuilder.toString();
        }
        for (int i = 0; i < employee.length - 1; i++) {
            stringBuilder.append(employee[i].getName()).append(" - ")
                    .append(employee[i].getSalary()).append("\n");
        }
        stringBuilder.append(employee[employee.length - 1].getName())
                .append(" - ").append(employee[employee.length - 1].getSalary());
        return stringBuilder.toString();
    }
}
