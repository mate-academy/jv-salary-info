package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_PART = 0;
    private static final int NAME_PART = 1;
    private static final int HOURS_PART = 2;
    private static final int RATE_PART = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String item : data) {
                String[] parts = item.split(" ");
                if (isWithinRange(parts[DATE_PART], dateFrom, dateTo)
                        && name.equals(parts[NAME_PART])) {
                    int hours = Integer.parseInt(parts[HOURS_PART]);
                    int rate = Integer.parseInt(parts[RATE_PART]);
                    salary += hours * rate;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }

    private static boolean isWithinRange(String date, String dateFrom, String dateTo) {
        try {
            Date current = dateFormat.parse(date);
            Date from = dateFormat.parse(dateFrom);
            Date to = dateFormat.parse(dateTo);
            return current.compareTo(from) >= 0 && current.compareTo(to) <= 0;
        } catch (ParseException e) {
            throw new RuntimeException("Can't get date, ", e);
        }
    }
}
