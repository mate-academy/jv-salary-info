package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String HEADER = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final String SELIMITER = " ";
    private static final int WORK_HOURS_INDEX_PARTS = 2;
    private static final int HOURLY_RATE_INDEX_PARTS = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder(HEADER)
                .append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo);
        for (String name : names) {
            int totalEarnings = 0;
            for (String record : data) {
                String[] parts = record.split(SELIMITER);
                String employeeName = parts[1];
                if (employeeName.equals(name)) {
                    if (isDateInRange(parts[0], dateFrom, dateTo)) {
                        int workHours = Integer.parseInt(parts[WORK_HOURS_INDEX_PARTS]);
                        int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX_PARTS]);
                        totalEarnings += workHours * hourlyRate;
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalEarnings);
        }
        return report.toString();
    }

    public static boolean isDateInRange(String dateStr, String dateFrom, String dateTo) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT);
            LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
            LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
            return !date.isBefore(fromDate) && !date.isAfter(toDate);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
