package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int EMPLOYEE_DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int EMPLOYEE_HOUR_INDEX = 2;
    private static final int EMPLOYEE_PRICE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] employeeData;
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        LocalDate localDateEmployee;
        int salary;
        StringBuilder builder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            salary = 0;
            for (String record : data) {
                employeeData = record.split(" ");

                if (employeeData[EMPLOYEE_NAME_INDEX].equals(name)) {
                    localDateEmployee =
                            LocalDate.parse(employeeData[EMPLOYEE_DATE_INDEX], DATE_FORMATTER);

                    if (isDateBetween(localDateEmployee, localDateFrom, localDateTo)) {
                        salary += (Integer.parseInt(employeeData[EMPLOYEE_HOUR_INDEX])
                                * Integer.parseInt(employeeData[EMPLOYEE_PRICE_INDEX]));
                    }
                }
            }
            builder.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }

    private boolean isDateBetween(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (date.isAfter(dateFrom) && date.isBefore(dateTo))
                || date.isEqual(dateFrom) || date.isEqual(dateTo);
    }
}
