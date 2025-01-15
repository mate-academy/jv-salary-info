package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\r\n");

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] splittedData = record.split(" ");
                LocalDate workDate = LocalDate.parse(splittedData[0], formatter);
                String employeeName = splittedData[1];
                int hoursWorked = Integer.parseInt(splittedData[2]);
                int hourlyRate = Integer.parseInt(splittedData[3]);

                if (employeeName.equals(name) &&
                        (workDate.isEqual(dateFromFormatted) || workDate.isAfter(dateFromFormatted)) &&
                        (workDate.isEqual(dateToFormatted) || workDate.isBefore(dateToFormatted))) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }
            builder.append(name).append(" - ").append(totalSalary).append("\r\n");
        }
        return builder.toString().trim();
    }

}
