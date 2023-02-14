package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = createEmployees(names);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo);
        LocalDate dateStartPeriod = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEndPeriod = LocalDate.parse(dateTo, FORMATTER);
        for (Employee employee : employees) {
            String employeeName = employee.getName();
            for (String d : data) {
                DataField dataField = createDataField(d);
                String nameInData = dataField.getEmployeeName();
                LocalDate dateInData = LocalDate.parse(dataField.getDate(), FORMATTER);
                if (nameInData.equals(employeeName)
                        && (!dateInData.isBefore(dateStartPeriod)
                        && !dateInData.isAfter(dateEndPeriod))) {
                    employee.calculateSalaryPerDay(dataField.getWorkingHour(),
                            dataField.getIncomePerHour());
                }
            }
            stringBuilder
                    .append(System.lineSeparator())
                    .append(employee.getSalaryInfo());
        }
        return stringBuilder.toString();
    }

    private Employee[] createEmployees(String[] names) {
        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }
        return employees;
    }

    private DataField createDataField(String data) {
        String[] datas = data.split(" ");
        return new DataField(datas[0],
                            datas[1],
                            Integer.parseInt(datas[2]),
                            Integer.parseInt(datas[3]));
    }
}
