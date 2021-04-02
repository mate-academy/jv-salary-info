package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {

    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String NEW_STRING_DELIMITER = "\n";
    public static final String SPACE_DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate startOfPeriod = LocalDate.parse(dateFrom, formatter);
        LocalDate endOfPeriod = LocalDate.parse(dateTo, formatter);

        List<Employee> listEmployee = new ArrayList<>();

        for (String name : names) {
            listEmployee.add(new Employee(name, 0));
        }

        List<DataOfEmployees> dataEmployees = new ArrayList<>();

        for (String dataString : data) {
            String[] fieldsOfDataEmployee = dataString.split(SPACE_DELIMITER);
            LocalDate date = LocalDate.parse(fieldsOfDataEmployee[0], formatter);
            String employeeName = fieldsOfDataEmployee[1];
            int workedHours = Integer.parseInt(fieldsOfDataEmployee[2]);
            int salaryRate = Integer.parseInt(fieldsOfDataEmployee[3]);
            dataEmployees.add(new DataOfEmployees(date, employeeName, workedHours, salaryRate));
        }

        for (DataOfEmployees dataEmployee : dataEmployees) {
            if (dataEmployee.getDate().isAfter(startOfPeriod)
                    && dataEmployee.getDate().isBefore(endOfPeriod)) {
                for (Employee employee : listEmployee) {
                    if (employee.getName().equals(dataEmployee.getName())) {
                        int newSum = employee.getSalaryFromTo()
                                + (dataEmployee.getWorkedHours() * dataEmployee.getSalaryRate());
                        employee.setSalaryFromTo(newSum);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Employee employees : listEmployee) {
            sb.append(employees.toString()).append(NEW_STRING_DELIMITER);
        }

        return sb.toString();
    }
}
