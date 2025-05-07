package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int CURRENT_DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURS_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);
        if (data.length > 0) {
            for (String name : names) {
                int totalSalary = calculateTotalSalaryForEmployee(name, data, fromDate, toDate);
                result.append(System.lineSeparator())
                        .append(name).append(" - ").append(totalSalary);
            }
        }
        return "Report for period " + dateFrom + " - " + dateTo + result;
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    private int calculateTotalSalaryForEmployee(String name, String[] data,
                                                LocalDate fromDate, LocalDate toDate) {
        int totalSalary = 0;
        for (String datum : data) {
            String[] date = datum.split(" ");
            LocalDate currentDate = LocalDate.parse(date[CURRENT_DATE_INDEX], DATE_TIME_FORMATTER);
            String employeeName = date[EMPLOYEE_NAME_INDEX];
            int hoursWorked = Integer.parseInt(date[HOURS_WORKED_INDEX]);
            int hoursRate = Integer.parseInt(date[HOURS_RATE_INDEX]);
            int salary = hoursRate * hoursWorked;
            if (!currentDate.isAfter(toDate)
                    && !currentDate.isBefore(fromDate)
                    && employeeName.equals(name)) {
                totalSalary += salary;
            }
        }
        return totalSalary;
    }
}
