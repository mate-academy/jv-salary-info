package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] earnings = calculateEarnings(names, data, dateFrom, dateTo);
        return generateReport(names, earnings, dateFrom, dateTo);
    }

    private int[] calculateEarnings(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] earnings = new int[names.length];
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);
            for (String str : data) {
                String[] parts = str.split(" ");
                Date targetDate = dateFormat.parse(parts[DATE_INDEX]);
                if (targetDate.compareTo(fromDate) >= 0 && targetDate.compareTo(toDate) <= 0) {
                    String name = parts[NAME_INDEX];
                    int hours = Integer.parseInt(parts[HOURS_INDEX]);
                    int moneyPerHour = Integer.parseInt(parts[MONEY_PER_HOUR_INDEX]);
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            earnings[i] += hours * moneyPerHour;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("Corrupted data");
        }
        return earnings;
    }

    private String generateReport(String[] names, int[] earnings, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(earnings[i]);
        }
        return report.toString();
    }
}
