package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                String recordDateStr = parts[0];
                LocalDate localDate = LocalDate.parse(recordDateStr, formatter);
                String employeeName = parts[1];
                int hoursWorked = Integer.parseInt(parts[2]);
                int hourlyRate = Integer.parseInt(parts[3]);

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
