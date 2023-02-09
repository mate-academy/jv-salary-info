package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKING_DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKING_HOUR = 2;
    private static final int INCOME_PER_HOUR = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo);

        LocalDate limitFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate limitTo = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int employeeSalary = 0;
            for (String dataString : data) {
                String[] employeeInfo = dataString.split(" ");
                if (employeeInfo[EMPLOYEE_NAME].equals(name)) {
                    LocalDate workingDate = LocalDate.parse(employeeInfo[WORKING_DATE], formatter);
                    if ((workingDate.isAfter(limitFrom) || workingDate.isEqual(limitFrom))
                            && (workingDate.isBefore(limitTo) || workingDate.isEqual(limitTo))
                            && (!limitFrom.isEqual(limitTo))) {
                        employeeSalary += Integer.parseInt(employeeInfo[WORKING_HOUR])
                                * Integer.parseInt(employeeInfo[INCOME_PER_HOUR]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name + " - " + employeeSalary);
        }
        return stringBuilder.toString();
    }
}
