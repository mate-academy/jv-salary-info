
package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {

        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        if (startDate.isAfter(endDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], DATE_FORMATTER);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);
            int salaryForDay = hoursWorked * hourlyRate;

            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += salaryForDay;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
