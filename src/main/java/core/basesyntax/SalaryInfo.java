package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        int totalSalary = 0;

        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int employeeSalary = calculateEmployeeSalary(name,data,fromDate,toDate);
            report.append("\n").append(name).append(" - ").append(employeeSalary);
            totalSalary += employeeSalary;
        }

        return report.toString();
    }

    private static int calculateEmployeeSalary(String name, String[] data,
                                               LocalDate fromDate, LocalDate toDate) {
        int salary = 0;

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate date = LocalDate.parse(parts[0],DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String employeeName = parts[1];

            if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0
                    && employeeName.equals(name)) {
                int hours = Integer.parseInt(parts[2]);
                int hourlyRate = Integer.parseInt(parts[3]);
                salary += hourlyRate * hours;
            }
        }
        return salary;
    }
}
