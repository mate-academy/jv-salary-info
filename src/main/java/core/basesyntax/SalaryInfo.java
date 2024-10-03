package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);

        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();

        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {

                String[] recordParts = record.split(" ");

                LocalDate recordDate = LocalDate.parse(recordParts[0], formatter);
                String employeeName = recordParts[1];
                int hoursWorked = Integer.parseInt(recordParts[2]);
                int hourlyRate = Integer.parseInt(recordParts[3]);

                if (employeeName.equals(name) && !recordDate.isBefore(fromDate)
                        && !recordDate.isAfter(toDate)) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }

            report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
