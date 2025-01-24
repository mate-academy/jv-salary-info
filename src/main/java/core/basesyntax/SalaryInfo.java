package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    // Головний метод, який реалізує логіку підрахунку зарплати
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Крок 1: Перетворити строки dateFrom і dateTo у формат LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), formatter);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate recordDate = LocalDate.parse(recordParts[0], formatter);
                String employeeName = recordParts[1];
                int hoursWorked = Integer.parseInt(recordParts[2]);
                int hourlyRate = Integer.parseInt(recordParts[3]);

                if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)
                        && employeeName.equals(name)) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }

            report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}