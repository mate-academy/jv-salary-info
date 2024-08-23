package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sums = new int[names.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(parts[0], formatter);

            if ((entryDate.isEqual(from) || entryDate.isAfter(from)) && (entryDate.isEqual(to) || entryDate.isBefore(to))) {
                String employeeName = parts[1];
                int hoursWorked = Integer.parseInt(parts[2]);
                int salaryPerHour = Integer.parseInt(parts[3]);

                for (int j = 0; j < names.length; j++) {
                    if (employeeName.equals(names[j])) {
                        sums[j] += salaryPerHour * hoursWorked;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(sums[i]);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
