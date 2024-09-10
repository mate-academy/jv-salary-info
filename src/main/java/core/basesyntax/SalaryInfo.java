package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(dateFrom.trim(), formatter);
            endDate = LocalDate.parse(dateTo.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. "
                    + "Expected format: dd.MM.yyyy", e);
        }

        HashMap<String, Integer> salaries = new HashMap<>();
        for (String name : names) {
            salaries.put(name, 0);
        }

        for (String employeeRecord : data) {
            String[] employeeDetails = employeeRecord.split(" ");
            LocalDate date;
            try {
                date = LocalDate.parse(employeeDetails[0], formatter);
                String employeeName = employeeDetails[1];
                int hoursWorked = Integer.parseInt(employeeDetails[2]);
                int hourlyRate = Integer.parseInt(employeeDetails[3]);

                if (!startDate.isAfter(date) && !endDate.isBefore(date)
                        && salaries.containsKey(employeeName)) {
                    salaries.put(employeeName, salaries.get(employeeName)
                            + hoursWorked * hourlyRate);
                }
            } catch (DateTimeParseException | NumberFormatException ex) {
                System.out.println("Error in record: " + employeeRecord);
            }
        }

        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());

        for (String name : names) {
            result.append(name).append(" - ").append(salaries.get(name));
            result.append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
