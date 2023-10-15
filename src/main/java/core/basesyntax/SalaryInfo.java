package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int totalEarnings = 0;
            for (String record : data) {
                String[] parts = record.split(" ");
                String employeeName = parts[1];
                if (employeeName.equals(name)) {
                    if (isDateInRange(parts[0], dateFrom, dateTo)) {
                        totalEarnings += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date1 = sdf.parse(dateStr);
            Date date2 = sdf.parse(dateFrom);
            Date date3 = sdf.parse(dateTo);
            if (date1.equals(date2) || date1.equals(date3)
                    || (date1.after(date2) && date1.before(date3))) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }
}
