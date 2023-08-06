package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate date = LocalDate.parse(recordParts[0], DATE_TIME_FORMATTER);
                if (date.isBefore(fromDate) || date.isAfter(toDate)) {
                    continue;
                }
                String nameAtRecord = recordParts[1];
                if (name.equals(nameAtRecord)) {
                    int hoursAtRecord = Integer.parseInt(recordParts[2]);
                    int moneyPerHour = Integer.parseInt(recordParts[3]);
                    int salary = moneyPerHour * hoursAtRecord;
                    totalSalary += salary;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }
}
