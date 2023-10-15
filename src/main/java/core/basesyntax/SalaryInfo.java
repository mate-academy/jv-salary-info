package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final String HEADER = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final String SELIMITER = " ";
    private static final int WORK_HOURS_INDEX_PARTS = 2;
    private static final int HOURLY_RATE_INDEX_PARTS = 3;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder(HEADER)
                .append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo)
                .append(System.lineSeparator());
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
            report.append(name).append(" - ").append(totalEarnings);
            if (!names[names.length - 1].equals(name)) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }

    private static boolean isDateInRange(String dateStr, String dateFrom, String dateTo) {
        try {
            Date date = DATE_FORMAT.parse(dateStr);
            Date fromDate = DATE_FORMAT.parse(dateFrom);
            Date toDate = DATE_FORMAT.parse(dateTo);
            return !date.before(fromDate) && !date.after(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
