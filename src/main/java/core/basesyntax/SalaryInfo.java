package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKING_HOUR = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate boundDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate boundDateTo = LocalDate.parse(dateTo, FORMATTER);
        int[] sumSalary = new int[names.length];
        for (String employeeData : data) {
            String[] employeeDataArray = employeeData.split(" ");
            LocalDate workingDate = LocalDate.parse(employeeDataArray[0], FORMATTER);
            if ((workingDate.isEqual(boundDateFrom) || workingDate.isAfter(boundDateFrom))
                    && (workingDate.isEqual(boundDateTo) || workingDate.isBefore(boundDateTo))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeDataArray[EMPLOYEE_NAME])) {
                        sumSalary[i] += Integer.parseInt(employeeDataArray[WORKING_HOUR])
                                * Integer.parseInt(employeeDataArray[INCOME_PER_HOUR]);
                    }
                }
            }
        }
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator());
            report.append(names[i]);
            report.append(" - ");
            report.append(sumSalary[i]);
        }
        return report.toString();
    }

}
