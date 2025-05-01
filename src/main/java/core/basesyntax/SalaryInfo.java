package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int johnSalary = 0;
        int andrewSalary = 0;
        int kateSalary = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        for (String record : data) {
            String[] parts = record.split(" ");
            String dateStr = parts[0];
            String name = parts[1];
            final int hours = Integer.parseInt(parts[2]);
            final int rate = Integer.parseInt(parts[3]);
            LocalDate date = LocalDate.parse(dateStr, formatter);

            if (date.isBefore(startDate) || date.isAfter(endDate)) {
                continue;
            }

            boolean isEmployeeInList = false;
            for (String employeeName : names) {
                if (employeeName.equals(name)) {
                    isEmployeeInList = true;
                    break;
                }
            }
            if (!isEmployeeInList) {
                continue;
            }

            int salary = hours * rate;

            if (name.equals("John")) {
                johnSalary += salary;
            } else if (name.equals("Andrew")) {
                andrewSalary += salary;
            } else if (name.equals("Kate")) {
                kateSalary += salary;
            }
        }
        return ("Report for period " + dateFrom + " - " + dateTo + "\n"
                + "John - " + johnSalary + "\n"
                + "Andrew - " + andrewSalary + "\n"
                + "Kate - " + kateSalary);
    }
}
