package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\r\n");

        int index = 0;
        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] recordParts = record.split(" ");
                String recordDate = recordParts[0];

                if (record.contains(name) && isDateInRange(recordDate, dateFrom, dateTo)) {
                    int hoursWorked = Integer.parseInt(recordParts[2]);
                    int hourlyRate = Integer.parseInt(recordParts[3]);
                    totalSalary += hoursWorked * hourlyRate;
                }
            }

            index++;
            result.append(name).append(" - ").append(totalSalary);
            if (index != names.length) {
                result.append("\r\n");
            }
        }

        return result.toString();
    }

    private static boolean isDateInRange(String recordDate, String dateFrom, String dateTo) {
        LocalDate recordLocalDate = LocalDate.parse(recordDate,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate fromDate = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate toDate = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return !recordLocalDate.isBefore(fromDate) && !recordLocalDate.isAfter(toDate);
    }
}
