package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final String datePattern = "dd.MM.yyyy";
    private final int workDateIndex = 0;
    private final int workHoursIndex = 2;
    private final int hourSalaryIndex = 3;
    private final String dataSeparator = " ";
    private final String reportText = "Report for period ";
    private final String reportTextSeparator = " - ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate finishDate = LocalDate.parse(dateTo, formatter).plusDays(1);

        StringBuilder result = new StringBuilder(reportText)
                .append(dateFrom)
                .append(reportTextSeparator)
                .append(dateTo);
        int daySalary = 0;

        for (String name : names) {
            for (String employeeWorkingDayDatum : data) {
                String[] employeeInfo = employeeWorkingDayDatum.split(dataSeparator);
                LocalDate workDate = LocalDate.parse(employeeInfo[workDateIndex], formatter);
                if (employeeWorkingDayDatum.contains(name)
                        && workDate.isAfter(startDate)
                        && workDate.isBefore(finishDate)) {
                    daySalary += Integer.parseInt(employeeInfo[workHoursIndex])
                            * Integer.parseInt(employeeInfo[hourSalaryIndex]);
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(reportTextSeparator)
                    .append(daySalary);
            daySalary = 0;
        }

        return result.toString();
    }
}
