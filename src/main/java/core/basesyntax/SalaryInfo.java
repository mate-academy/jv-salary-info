package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA = 0;
    private static final int EMPLOYEE = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int fullSalary = 0;
        LocalDate dayFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dayTo = LocalDate.parse(dateTo, formatter);
        StringBuilder employeeInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String employeeName : names) {
            for (String dayInfo : data) {
                String[] arrayInfo = dayInfo.split(" ");
                LocalDate currentData = LocalDate.parse(arrayInfo[DATA], formatter);
                fullSalary += ((dayFrom.isBefore(currentData)) || dayFrom.equals(currentData))
                            && ((dayTo.isAfter(currentData)) || dayTo.equals(currentData))
                            && (arrayInfo[EMPLOYEE].equals(employeeName))
                            ? Integer.parseInt(arrayInfo[HOURS])
                            * Integer.parseInt(arrayInfo[SALARY]) : 0;
            }
            employeeInfo.append(System.lineSeparator())
                        .append(employeeName)
                        .append(" - ")
                        .append(fullSalary);
            fullSalary = 0;
        }
        return employeeInfo.toString();
    }
}
