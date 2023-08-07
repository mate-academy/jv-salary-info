package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int EMPLOYEE_DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int EMPLOYEE_HOUR_INDEX = 2;
    private static final int EMPLOYEE_PRICE_INDEX = 3;
    private static final int DATE_DAY_INDEX = 0;
    private static final int DATE_MONTH_INDEX = 1;
    private static final int DATE_YEAR_INDEX = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] employeeData;
        int[] employeeSalary = new int[names.length];

        for (String employee : data) {
            employeeData = employee.split(" ");

            if (isDateBetween(employeeData[EMPLOYEE_DATE_INDEX], dateFrom, dateTo)) {
                for (int numberOfName = 0; numberOfName < names.length; numberOfName++) {
                    if (names[numberOfName].equals(employeeData[EMPLOYEE_NAME_INDEX])) {
                        employeeSalary[numberOfName] +=
                                (Integer.parseInt(employeeData[EMPLOYEE_HOUR_INDEX])
                                        * Integer.parseInt(employeeData[EMPLOYEE_PRICE_INDEX]));
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int numberOfName = 0; numberOfName < names.length; numberOfName++) {
            builder.append(System.lineSeparator())
                    .append(names[numberOfName]).append(" - ")
                    .append(employeeSalary[numberOfName]);
        }

        return builder.toString();
    }

    private boolean isDateBetween(String date, String dateFrom, String dateTo) {

        String[] splitDate = date.split("\\.");
        String[] splitDateFrom = dateFrom.split("\\.");
        String[] splitDateTo = dateTo.split("\\.");

        LocalDate localDate = LocalDate.parse(splitDate[DATE_YEAR_INDEX]
                + "-" + splitDate[DATE_MONTH_INDEX] + "-" + splitDate[DATE_DAY_INDEX]);
        LocalDate localDateFrom = LocalDate.parse(splitDateFrom[DATE_YEAR_INDEX]
                + "-" + splitDateFrom[DATE_MONTH_INDEX] + "-" + splitDateFrom[DATE_DAY_INDEX]);
        LocalDate localDateTo = LocalDate.parse(splitDateTo[DATE_YEAR_INDEX]
                + "-" + splitDateTo[DATE_MONTH_INDEX] + "-" + splitDateTo[DATE_DAY_INDEX]);

        return (localDate.isAfter(localDateFrom) && localDate.isBefore(localDateTo))
                || localDate.isEqual(localDateFrom) || localDate.isEqual(localDateTo);
    }
}
