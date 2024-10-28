package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String startDate, String endDate)
            throws IllegalDateParametersException {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);

        if (start.isAfter(end)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder report = new StringBuilder();
        report.append("Отчёт за период ")
                .append(startDate)
                .append(" - ")
                .append(endDate)
                .append("\n");

        int[] totalSalaries = new int[names.length];

        for (String entry : data) {
            String[] entryParts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(entryParts[0], DATE_FORMATTER);

            if (!entryDate.isBefore(start) && !entryDate.isAfter(end)) {
                String employeeName = entryParts[1];
                int hoursWorked = Integer.parseInt(entryParts[2]);
                int hourlyRate = Integer.parseInt(entryParts[3]);
                int employeeIndex = findIndexByName(names, employeeName);

                if (employeeIndex != -1) {
                    totalSalaries[employeeIndex] += hoursWorked * hourlyRate;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(totalSalaries[i])
                    .append("\n");
        }

        return report.toString();
    }

    private int findIndexByName(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
