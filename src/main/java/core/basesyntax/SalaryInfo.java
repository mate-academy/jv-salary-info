package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        int[] totalSalary = new int[names.length];
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
            .append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (String line : data) {
            String[] parts = line.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                int index = -1;
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        index = i;
                        break;
                    }
                }
                int salary = hours * rate;
                totalSalary[index] += salary;
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(totalSalary[i]);
            if (i != names.length - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
