package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int LOCAL_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
            LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

            report.append(REPORT_HEADER)
                    .append(dateFrom).append(" - ")
                    .append(dateTo)
                    .append(LINE_SEPARATOR);

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                int totalMoneyEarned = 0;
                for (String info : data) {
                    String[] parts = info.split(" ");
                    LocalDate date = LocalDate.parse(parts[LOCAL_DATE_INDEX], DATE_FORMATTER);
                    if (name.equals(parts[NAME_INDEX]) && !date.isBefore(fromDate)
                            && !date.isAfter(toDate)) {
                        int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                        int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);
                        totalMoneyEarned += hoursWorked * hourlyRate;
                    }
                }
                report.append(name).append(" - ").append(totalMoneyEarned).append(LINE_SEPARATOR);
            }
            if (report.length() >= LINE_SEPARATOR.length()) {
                report.setLength(report.length() - LINE_SEPARATOR.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report.toString();
    }
}
