package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getLocalDate(dateFrom);
        LocalDate localDateTo = getLocalDate(dateTo);

        StringBuilder resultString = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        List<EmployeeSalary> listOfEmployeeSalaries = getListOfEmployeeSalaries(names);
        String date;
        String name;
        int hours;
        int salary;
        String[] row;
        LocalDate rowDate;

        for (EmployeeSalary employeeSalary : listOfEmployeeSalaries) {
            for (String record : data) {
                try {
                    row = record.split(" ");
                    date = row[0];
                    name = row[1];
                    hours = Integer.valueOf(row[2]);
                    salary = Integer.valueOf(row[3]);
                    rowDate = getLocalDate(date);
                    if (employeeSalary.getName().equals(name)
                            && rowDate.isAfter(localDateFrom)
                            && !rowDate.isAfter(localDateTo)) {
                        employeeSalary.addSalary(salary * hours);
                    }
                } catch (DateTimeException
                         | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
            resultString.append(System.lineSeparator())
                    .append(employeeSalary.getName()).append(" - ")
                    .append(employeeSalary.getSalary());
        }
        return resultString.toString();
    }

    public List<EmployeeSalary> getListOfEmployeeSalaries(String[] names) {
        List<EmployeeSalary> employeeSalaries = new ArrayList<>();
        for (String name : names) {
            employeeSalaries.add(new EmployeeSalary(name));
        }
        return employeeSalaries;
    }

    public LocalDate getLocalDate(String date) {
        try {
            String[] dateArr = date.split("\\.");
            int day = Integer.valueOf(dateArr[0]);
            int month = Integer.valueOf(dateArr[1]);
            int year = Integer.valueOf(dateArr[2]);
            return LocalDate.of(year, month, day);
        } catch (DateTimeException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DateTimeException("Not valid date in: " + date);
        }
    }
}
