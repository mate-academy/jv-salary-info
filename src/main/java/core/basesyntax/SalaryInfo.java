package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormat);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormat);

        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = calculateTotalSalaryForEmployee(name, data, fromDate, toDate);
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }

    private int calculateTotalSalaryForEmployee(String employeeName, String[] data,
                                                LocalDate fromDate, LocalDate toDate) {
        int totalSalary = 0;

        for (String inception : data) {
            String[] parts = inception.split(" ");
            String entryDateOfEmployee = parts[0];
            String entryName = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int income = Integer.parseInt(parts[3]);

            LocalDate entryDate = LocalDate.parse(entryDateOfEmployee,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            if (entryName.equals(employeeName) && entryDate.compareTo(fromDate) >= 0
                    && entryDate.compareTo(toDate) <= 0) {
                totalSalary += (hours * income);
            }
        }
        return totalSalary;
    }
}

