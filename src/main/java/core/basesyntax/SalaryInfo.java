package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final int[] totalSalaries = new int[names.length];
        StringBuilder report = new StringBuilder();

        try {
            LocalDate from = LocalDate.parse(dateFrom, formatter);
            LocalDate to = LocalDate.parse(dateTo, formatter);
            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate date = LocalDate.parse(recordParts[0], formatter);
                String name = recordParts[1];
                final int hoursWorked = Integer.parseInt(recordParts[2]);
                final int hourlyRate = Integer.parseInt(recordParts[3]);

                if ((date.isEqual(from) || date.isAfter(from))
                        && (date.isEqual(to) || date.isBefore(to))) {

                    int index = -1;
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            index = i;
                            break;
                        }
                    }

                    if (index != -1) {
                        totalSalaries[index] += hoursWorked * hourlyRate;
                    }
                }
            }

            report.append("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo)
                    .append(System.lineSeparator());

            for (int i = 0; i < names.length; i++) {
                report.append(names[i])
                        .append(" - ")
                        .append(totalSalaries[i]);
                if (i != names.length - 1) {
                    report.append(System.lineSeparator());
                }
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        return report.toString();
    }
}
