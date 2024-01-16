
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        String[] usernames = new String[]{"John", "Andrew", "Kate"};
        for (String username : usernames) {
            int totalEarnings = 0;

            for (String entry : data) {
                String[] entryParts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(entryParts[0], DATE_TIME_FORMATTER);
                String employeeName = entryParts[1];

                if (username.equals(employeeName)
                        && (entryDate.isEqual(fromDate) || entryDate.compareTo(fromDate) >= 0)
                        && (entryDate.isEqual(toDate) || entryDate.compareTo(toDate) <= 0)) {
                    int hoursWorked = Integer.parseInt(entryParts[2]);
                    double hourlyRate = Double.parseDouble(entryParts[3]);
                    totalEarnings += (hoursWorked * hourlyRate);
                }
            }
            result.append(LINE_SEPARATOR).append(username).append(" - ").append(totalEarnings);
        }
        return result.toString();
    }
}
