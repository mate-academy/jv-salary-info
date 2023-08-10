package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int EMPLOYEE_DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int EMPLOYEE_HOUR_INDEX = 2;
    private static final int EMPLOYEE_PRICE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] employeeData;
        int salary;
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            salary = 0;
            for (String record : data) {
                employeeData = record.split(" ");

                if (employeeData[EMPLOYEE_NAME_INDEX].equals(name)
                        && isDateBetween(employeeData[EMPLOYEE_DATE_INDEX], dateFrom, dateTo)) {
                    salary += (Integer.parseInt(employeeData[EMPLOYEE_HOUR_INDEX])
                            * Integer.parseInt(employeeData[EMPLOYEE_PRICE_INDEX]));
                }
            }
            builder.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }

    private boolean isDateBetween(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        LocalDate localDate =
                LocalDate.parse(date, formatter);
        LocalDate localDateFrom =
                LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo =
                LocalDate.parse(dateTo, formatter);

        return (localDate.isAfter(localDateFrom) && localDate.isBefore(localDateTo))
                || localDate.isEqual(localDateFrom) || localDate.isEqual(localDateTo);
    }
}
