package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] recordParts = record.split(" ");
                String recordDateStr = recordParts[DATE_INDEX];
                LocalDate localDate = LocalDate.parse(recordDateStr, formatter);
                String employeeName = recordParts[NAME_INDEX];
                int hoursWorked = Integer.parseInt(recordParts[HOURS_WORKED_INDEX]);
                int hourlyRate = Integer.parseInt(recordParts[HOURLY_RATE_INDEX]);

                if (name.equals(employeeName)
                        && !localDate.isBefore(fromDate)
                        && !localDate.isAfter(toDate)) {
                    salary += hoursWorked * hourlyRate;

                }
            }
            builder.append(name).append(" - ").append(salary);

            if (!name.equals(names[names.length - 1])) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
