package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        int[] salaries = new int[names.length];

        try {
            long fromDate = dateFormat.parse(dateFrom).getTime();
            long toDate = dateFormat.parse(dateTo).getTime();

            for (String entry : data) {
                try {
                    String[] parts = entry.split(" ");
                    if (parts.length != 4) {
                        throw new IllegalArgumentException("Invalid data format: " + entry);
                    }

                    String workDateStr = parts[0];
                    String workerName = parts[1];
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int hourlyRate = Integer.parseInt(parts[3]);

                    long workDate = dateFormat.parse(workDateStr).getTime();

                    if (workDate >= fromDate && workDate <= toDate) {
                        for (int i = 0; i < names.length; i++) {
                            if (names[i].equals(workerName)) {
                                salaries[i] += hoursWorked * hourlyRate;
                                break;
                            }
                        }
                    }
                } catch (ParseException e) {
                    System.err.println("Skipping invalid date format: " + entry);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number format: " + entry);
                }
            }
        } catch (ParseException e) {
            return "Error parsing dates.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return report.toString();
    }
}
