package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int fullSalary = 0;
        LocalDate dayFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dayTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder employeeInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String employeeName : names) {
            for (String dayInfo : data) {
                String[] arrayInfo = dayInfo.split(" ");
                LocalDate currentData = LocalDate.parse(arrayInfo[DATE_INDEX], FORMATTER);
                if (((dayFrom.isBefore(currentData)) || dayFrom.equals(currentData))
                            && ((dayTo.isAfter(currentData)) || dayTo.equals(currentData))
                            && (arrayInfo[EMPLOYEE_NAME_INDEX].equals(employeeName))) {
                    fullSalary += Integer.parseInt(arrayInfo[HOURS_INDEX])
                                * Integer.parseInt(arrayInfo[SALARY_PER_HOUR_INDEX]);
                }
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
