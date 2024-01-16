
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int DATE_PART_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String username : names) {
            int totalEarnings = 0;

            for (String entry : data) {
                String[] entryParts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(entryParts[DATE_PART_INDEX],
                        DATE_TIME_FORMATTER);
                String employeeName = entryParts[EMPLOYEE_NAME_INDEX];

                if (username.equals(employeeName)
                        && (entryDate.isEqual(fromDate) || entryDate.compareTo(fromDate) >= 0)
                        && (entryDate.isEqual(toDate) || entryDate.compareTo(toDate) <= 0)) {
                    int hoursWorked = Integer.parseInt(entryParts[HOURS_WORKED_INDEX]);
                    double hourlyRate = Double.parseDouble(entryParts[HOURLY_RATE_INDEX]);
                    totalEarnings += (hoursWorked * hourlyRate);
                }
            }
            result.append(LINE_SEPARATOR).append(username).append(" - ").append(totalEarnings);
        }
        return result.toString();
    }
}
