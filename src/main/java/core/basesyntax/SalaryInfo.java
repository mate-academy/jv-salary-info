package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder report = new StringBuilder();
        int[] earnings = new int[names.length];

        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = sdf.parse(dateFrom);
            toDate = sdf.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            if (parts.length == 4) {
                try {
                    Date entryDate = sdf.parse(parts[0]);
                    if (!entryDate.after(toDate) && !entryDate.before(fromDate)) {
                        String name = parts[1];
                        int hoursWorked = Integer.parseInt(parts[2]);
                        int incomePerHour = Integer.parseInt(parts[3]);
                        for (int i = 0; i < names.length; i++) {
                            if (names[i].equals(name)) {
                                earnings[i] += hoursWorked * incomePerHour;
                                break;
                            }
                        }
                    }
                } catch (ParseException e) {
                    throw new RuntimeException("Invalid date format in entry date", e);
                }
            } else {
                return "Invalid data entry format";
            }
        }

        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(earnings[i]);
        }

        return report.toString();
    }
}
