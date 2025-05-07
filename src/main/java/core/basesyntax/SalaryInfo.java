package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int RATE_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] splitString = record.split(" ");
                String date = splitString[DATE_POSITION];
                String employeeName = splitString[NAME_POSITION];
                int hoursWorked = Integer.parseInt(splitString[HOURS_POSITION]);
                int payRate = Integer.parseInt(splitString[RATE_POSITION]);
                LocalDate recordDate = LocalDate.parse(date, FORMATTER);
                if (name.equals(employeeName) && !recordDate.isBefore(startDate)
                        && !recordDate.isAfter(endDate)) {
                    totalSalary += hoursWorked * payRate;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return builder.toString();
    }
}
