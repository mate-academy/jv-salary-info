package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name: names) {
            int salary = 0;
            LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
            for (String databaseRecord : data) {
                String[] dataRecord = databaseRecord.split(" ");
                String employeeName = dataRecord[NAME_INDEX];
                LocalDate recordDate = LocalDate.parse(dataRecord[DATE_INDEX], FORMATTER);
                if (employeeName.equals(name)
                        && !recordDate.isBefore(fromDate)
                        && !recordDate.isAfter(toDate)) {
                    int hoursWorked = Integer.parseInt(dataRecord[HOURS_WORKED_INDEX]);
                    int hourlyRate = Integer.parseInt(dataRecord[HOURLY_RATE_INDEX]);
                    salary += hoursWorked * hourlyRate;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
