package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_INDEX = "0";
    private static final String NAME_INDEX = "1";
    private static final String HOURS_WORKED_INDEX = "2";
    private static final String HOURLY_RATE_INDEX = "3";
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int totalIncome = 0;
            report.append(System.lineSeparator());

            for (String recordData : data) {
                String[] recordParts = recordData.split(" ");

                String recordDate = recordParts[Integer.parseInt(DATE_INDEX)];
                String recordName = recordParts[Integer.parseInt(NAME_INDEX)];
                int hoursWorked = Integer.parseInt(recordParts[Integer
                        .parseInt(HOURS_WORKED_INDEX)]);
                int hourlyRate = Integer.parseInt(recordParts[Integer
                        .parseInt(HOURLY_RATE_INDEX)]);

                if (name.equals(recordName)) {
                    LocalDate localDate = LocalDate.parse(recordDate, DATE_FORMATTER);

                    if (!localDate.isBefore(localDateFrom) && !localDate.isAfter(localDateTo)) {
                        totalIncome += hoursWorked * hourlyRate;
                    }
                }
            }

            report.append(name).append(" - ").append(totalIncome);
        }

        return report.toString();
    }
}
