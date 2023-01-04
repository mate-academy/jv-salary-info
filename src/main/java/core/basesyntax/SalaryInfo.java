package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] res = new int[names.length];
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date1;
        Date date2;
        try {
            date1 = format.parse(dateFrom);
            date2 = format.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                String[] dates = datum.split(" ");
                try {
                    if (names[i].equals(dates[1])
                            && format.parse(dates[0]).getTime() >= date1.getTime()
                            && format.parse(dates[0]).getTime() <= date2.getTime()) {
                        res[i] += (Integer.parseInt(dates[2]) * Integer.parseInt(dates[3]));
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        String result = "Report for period " + dateFrom + " - " + dateTo;
        for (int i = 0; i < res.length; i++) {
            result += "\r\n" + names[i] + " - " + res[i];
        }
        return result.trim();
    }
}

