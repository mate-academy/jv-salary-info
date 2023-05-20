package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int PER_HOUR_SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String dates : data) {
                String[] employeeInfo = dates.split(" ");
                if (employeeInfo[NAME_INDEX].equals(name)
                        && isDateTheSameOrBetween(employeeInfo[DATE_INDEX], dateFrom, dateTo)) {
                    totalSalary += Integer.parseInt(employeeInfo[WORKED_HOURS_INDEX])
                            * Integer.parseInt(employeeInfo[PER_HOUR_SALARY_INDEX]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return builder.toString().trim();
    }

    private boolean isDateTheSameOrBetween(String dateValidate, String dateFrom, String dateTo) {
        LocalDate validateDate = LocalDate.parse(dateValidate, FORMATTER);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);;

        return validateDate.equals(fromDate) || validateDate.isAfter(fromDate)
                && validateDate.isBefore(toDate) || validateDate.equals(toDate);
    }
}
