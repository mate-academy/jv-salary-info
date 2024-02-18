package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private static final String NAME_AND_SALARY_SEPARATOR = " - ";
    private static final String SALARY_INFO_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getLocalDate(dateFrom);
        LocalDate localDateTo = getLocalDate(dateTo);
        StringBuilder resultString = new StringBuilder(SALARY_INFO_HEADER)
                .append(dateFrom).append(NAME_AND_SALARY_SEPARATOR)
                .append(dateTo);
        String date;
        String name;
        int hours;
        int salary;
        String[] row;
        LocalDate rowDate;
        EmployeeSalary employeeSalary;
        for (String employeeName : names) {
            employeeSalary = new EmployeeSalary(employeeName);
            for (String record : data) {
                row = record.split(" ");
                date = getDateFromRow(row);
                rowDate = getLocalDate(date);
                name = getNameFromRow(row);
                if (employeeSalary.getName().equals(name)
                        && rowDate.isAfter(localDateFrom)
                        && !rowDate.isAfter(localDateTo)) {
                    hours = getHoursFromRow(row);
                    salary = getSalaryFromRow(row);
                    employeeSalary.addSalary(salary * hours);
                }
            }
            resultString.append(System.lineSeparator())
                    .append(employeeSalary.getName()).append(NAME_AND_SALARY_SEPARATOR)
                    .append(employeeSalary.getSalary());
        }
        return resultString.toString();
    }

    public LocalDate getLocalDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeException e) {
            throw new DateTimeException("Not valid date in: " + date);
        }
    }

    private String getNameFromRow(String[] row) {
        return row[INDEX_OF_NAME];
    }

    private String getDateFromRow(String[] row) {
        return row[INDEX_OF_DATE];
    }

    private int getSalaryFromRow(String[] row) {
        return Integer.valueOf(row[INDEX_OF_SALARY]);
    }

    private int getHoursFromRow(String[] row) {
        return Integer.valueOf(row[INDEX_OF_HOURS]);
    }

}
