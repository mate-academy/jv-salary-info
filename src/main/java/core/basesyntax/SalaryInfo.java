package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalIncome = 0;

            for (String recordData : data) {
                String[] recordParts = recordData.split(" ");

                String recordDate = recordParts[0];
                String recordName = recordParts[1];
                int hoursWorked = Integer.parseInt(recordParts[2]);
                int hourlyRate = Integer.parseInt(recordParts[3]);

                if (name.equals(recordName)) {
                    LocalDate localDate = LocalDate.parse(recordDate, DATE_FORMATTER);

                    if (!localDate.isBefore(localDateFrom) && !localDate.isAfter(localDateTo)) {
                        totalIncome += hoursWorked * hourlyRate;
                    }
                }
            }

            report.append(name).append(" - ").append(totalIncome);
            if (!name.equals(names[names.length - 1])) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}

