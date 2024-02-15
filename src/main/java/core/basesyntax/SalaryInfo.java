package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        try {
            Date fromDate = DATE_FORMAT.parse(dateFrom);
            Date toDate = DATE_FORMAT.parse(dateTo);

            report.append(REPORT_HEADER)
                    .append(dateFrom).append(" - ")
                    .append(dateTo)
                    .append(LINE_SEPARATOR);

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                int totalMoneyEarned = 0;
                for (String info : data) {
                    String[] parts = info.split(" ");
                    Date date = DATE_FORMAT.parse(parts[0]);
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return report.toString();
    }
}
