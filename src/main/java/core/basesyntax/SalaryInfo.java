package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        if (data.length > 0) {
            String reportPeriod = "Report for period " + dateFrom + " - " + dateTo;
            result.insert(0, reportPeriod);
            for (String name : names) {
                StringBuilder nameResult = new StringBuilder();
                int totalSalary = 0;
                for (String datum : data) {
                    String[] date = datum.split(" ");
                    LocalDate currentDate = LocalDate.parse(date[0], DATE_TIME_FORMATTER);
                    String employeeName = date[1];
                    int hoursWorked = Integer.parseInt(date[2]);
                    int hoursRate = Integer.parseInt(date[3]);
                    int salary = hoursRate * hoursWorked;
                    if ((currentDate.isAfter(fromDate) || currentDate.isEqual(fromDate))
                            && (currentDate.isBefore(toDate) || currentDate.isEqual(toDate))
                            && employeeName.equals(name)) {
                        totalSalary += salary;
                    }
                }
                nameResult.append(name).append(" - ").append(totalSalary);
                result.append(System.lineSeparator()).append(nameResult);
            }
        }
        return result.toString();
    }
}
