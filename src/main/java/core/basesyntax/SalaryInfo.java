package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sum = new int[names.length];
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = null;
        try {
            date1 = sdf.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date date2 = null;
        try {
            date2 = sdf.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < data.length; i++) {
            String[] dataArray = data[i].split(" ");
            Date date3 = null;
            try {
                date3 = sdf.parse(dataArray[0]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                for (int k = 0; k < names.length; k++) {
                    if (names[k].equals(dataArray[1])) {
                        sum[k] += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            result.append(names[i] + " - " + sum[i] + System.lineSeparator());
        }
        return result.toString().trim();
    }
}
