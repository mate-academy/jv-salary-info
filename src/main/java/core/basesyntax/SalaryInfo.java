package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String dates : data) {
                String[] employeeInfo = dates.split(" ");
                if (employeeInfo[1].equals(name)) {
                    if (isDateTheSameOrBetween(employeeInfo[0], dateFrom, dateTo)) {
                        totalSalary += Integer.parseInt(employeeInfo[2])
                                * Integer.parseInt(employeeInfo[3]);
                    }
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
