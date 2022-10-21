package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] tmpStrArr;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        int len = names.length;
        int[] tmpIntArr = new int[len];
        StringBuilder stringBuilder =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        Date tmpDate;
        Date tmpDateFrom;
        Date tmpDateTo;
        try {
            tmpDateFrom = formatter.parse(dateFrom);
        } catch (ParseException e) {
            tmpDateFrom = new Date();
        }
        try {
            tmpDateTo = formatter.parse(dateTo);
        } catch (ParseException e) {
            tmpDateTo = new Date();
        }

        for (String d: data) {
            tmpStrArr = d.split(" ");
            try {
                tmpDate = formatter.parse(tmpStrArr[0]);
            } catch (ParseException e) {
                tmpDate = new Date();
            }
            if (tmpDate.after(tmpDateFrom) && tmpDate.before(tmpDateTo)
                    || tmpDate.compareTo(tmpDateFrom) == 0
                    || tmpDate.compareTo(tmpDateTo) == 0) {
                for (int i = 0; i < len; ++i) {
                    if (names[i].equals(tmpStrArr[1])) {
                        try {
                            tmpIntArr[i] +=
                                    Integer.parseInt(tmpStrArr[2]) * Integer.parseInt(tmpStrArr[3]);
                        } catch (NumberFormatException e) {
                            tmpIntArr[i] += 0;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < len; ++i) {
            stringBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(tmpIntArr[i]);
        }
        return stringBuilder.toString();
    }
}
