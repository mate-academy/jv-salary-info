package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private static final String DELIMITER = " - ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String ABOUT_PERIOD = "Report for period ";
    private static final int INDEX_IN_PARS_STRING_DATA_DATE = 0;
    private static final int INDEX_IN_PARS_STRING_DATA_NAME = 1;
    private static final int INDEX_IN_PARS_STRING_DATA_HOURS = 2;
    private static final int INDEX_IN_PARS_STRING_DATA_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, format);
        LocalDate localDateTo = LocalDate.parse(dateTo, format);
        LocalDate dateToCheck;
        String[] employeInfo;
        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
            for (String personData : data) {
                employeInfo = personData.split(SEPARATOR);
                dateToCheck = LocalDate.parse(employeInfo[INDEX_IN_PARS_STRING_DATA_DATE], format);
                if (dateToCheck.isEqual(localDateFrom) || dateToCheck.isEqual(localDateTo)
                        || (dateToCheck.isAfter(localDateFrom)
                        && dateToCheck.isBefore(localDateTo))) {
                    if (employeInfo[INDEX_IN_PARS_STRING_DATA_NAME]
                            .equals(employees[i].getName())) {
                        employees[i].setSalary(employees[i].getSalary()
                                + Integer.parseInt(employeInfo[INDEX_IN_PARS_STRING_DATA_SALARY])
                                * Integer.parseInt(employeInfo[INDEX_IN_PARS_STRING_DATA_HOURS]));
                    }
                }
            }
        }
        return createReport(employees,dateFrom,dateTo);
    }

    public String createReport(Employee[] employees, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append(ABOUT_PERIOD).append(dateFrom).append(DELIMITER)
               .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < employees.length; i++) {
            result.append(employees[i].getName())
                    .append(DELIMITER).append(employees[i].getSalary());
            if (i < employees.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
