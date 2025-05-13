package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String WORD_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String entry : data) {
                String[] details = entry.split(WORD_SEPARATOR);
                LocalDate workDate = LocalDate.parse(details[0], DATE_FORMATTER);
                String employeeName = details[1];
                int hoursWorked = Integer.parseInt(details[2]);
                int hourlyRate = Integer.parseInt(details[3]);

                if (employeeName.equals(name)
                        && !workDate.isBefore(startDate)
                        && !workDate.isAfter(endDate)) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }

            report.append(System.lineSeparator())
                    .append(name).append(" - ").append(totalSalary);
        }

        return report.toString();
    }
}
