package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String res = "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator();
        StringBuilder builder = new StringBuilder(res);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date fromDate = null;
        try {
            fromDate = format.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date toDate = null;

        try {
            toDate = format.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int[] allSalaries = new int[names.length];
        for (int j = 0; j < data.length; j++) {
            Date currentDate = null;
            try {
                currentDate = format.parse(data[j].split(" ")[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int hoursCounter = Integer.parseInt(data[j].split(" ")[2]);
            int cashPerHour = Integer.parseInt(data[j].split(" ")[3]);
            for (int i = 0; i < names.length; i++) {
                if (data[j].split(" ")[1].equals(names[i]) && (currentDate.equals(fromDate)
                        || currentDate.after(fromDate)) && (currentDate.equals(toDate)
                        || currentDate.before(toDate))) {
                    allSalaries[i] += hoursCounter * cashPerHour;
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i] + " - " + allSalaries[i] + System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
