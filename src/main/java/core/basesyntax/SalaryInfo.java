package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
            LocalDate toDate = LocalDate.parse(dateTo, formatter);

            if (fromDate.isAfter(toDate)) {
                return "Error: fromDate cannot be after toDate.";
            }

            int[] salaries = new int[names.length];

            for (String entry : data) {
                try {
                    String[] parts = entry.split(" ");
                    if (parts.length != 4) {
                        return "Error: Invalid data format in entry - " + entry;
                    }

                    LocalDate workDate = LocalDate.parse(parts[0], formatter);
                    String workerName = parts[1];
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int hourlyRate = Integer.parseInt(parts[3]);

                    if (!workDate.isBefore(fromDate) && !workDate.isAfter(toDate)) {
                        for (int i = 0; i < names.length; i++) {
                            if (names[i].equals(workerName)) {
                                salaries[i] += hoursWorked * hourlyRate;
                                break;
                            }
                        }
                    }
                } catch (DateTimeParseException e) {
                    return "Error parsing date in entry - " + entry;
                } catch (NumberFormatException e) {
                    return "Error parsing numbers in entry - " + entry;
                }
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

        } catch (DateTimeParseException e) {
            return "Error parsing dates.";
        }
    }
}
