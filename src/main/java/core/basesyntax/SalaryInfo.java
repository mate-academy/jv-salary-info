package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

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
                    LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER);
                    if (name.equals(parts[1]) && date.compareTo(fromDate) >= 0
                            && date.compareTo(toDate) <= 0) {
                        int hoursWorked = Integer.parseInt(parts[2]);
                        int hourlyRate = Integer.parseInt(parts[3]);
                        totalMoneyEarned += hoursWorked * hourlyRate;
                    }
                }
                report.append(name).append(" - ").append(totalMoneyEarned);
                if (i != names.length - 1) {
                    report.append(LINE_SEPARATOR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report.toString();
    }
}
