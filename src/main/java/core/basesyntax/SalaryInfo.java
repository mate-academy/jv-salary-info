package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] earnings = new int[names.length];
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);
            for (String str : data) {
                String[] parts = str.split(" ");
                Date targetDate = dateFormat.parse(parts[0]);
                if (targetDate.compareTo(fromDate) >= 0 && targetDate.compareTo(toDate) <= 0) {
                    String name = parts[1];
                    int hours = Integer.parseInt(parts[2]);
                    int moneyPerHour = Integer.parseInt(parts[3]);
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            earnings[i] = earnings[i] + hours * moneyPerHour;
                        }
                    }

                }
            }
        } catch (ParseException e) {
            System.out.println("Corrupted data");
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(earnings[i]);
        }
        return report.toString();
    }
}
