package core.basesyntax;

import core.utils.DateFormatter;
import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String dates : data) {
                if (dates.contains(name)) {
                    String[] employeeInfo = dates.split(" ");
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
        LocalDate validateDate = DateFormatter.dateFormatter(dateValidate);
        LocalDate fromDate = DateFormatter.dateFormatter(dateFrom);
        LocalDate toDate = DateFormatter.dateFormatter(dateTo);

        return validateDate.equals(fromDate) || validateDate.isAfter(fromDate)
                && validateDate.isBefore(toDate) || validateDate.equals(toDate);
    }
}
