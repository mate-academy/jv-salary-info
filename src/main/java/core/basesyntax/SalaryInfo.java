package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(dateFrom.trim(), formatter);
            endDate = LocalDate.parse(dateTo.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. "
                    + "Expected format: dd.MM.yyyy", e);
        }

        int[] salaries = new int[data.length];

        for (String employeeRecord : data) {
            String[] employeeDetails = employeeRecord.split(" ");
            LocalDate date;
            try {
                date = LocalDate.parse(employeeDetails[0], formatter);
                String employeeName = employeeDetails[1];
                int hoursWorked = Integer.parseInt(employeeDetails[2]);
                int hourlyRate = Integer.parseInt(employeeDetails[3]);

                if (!startDate.isAfter(date) && !endDate.isBefore(date)) {
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(employeeName)) {
                            salaries[i] += hoursWorked * hourlyRate;
                            break;
                        }
                    }
                }
            } catch (DateTimeParseException | NumberFormatException ex) {
                System.out.println("Error in record: " + employeeRecord);
            }
        }

        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(salaries[i]).append("\n");
        }

        return result.toString().trim();
    }
}
