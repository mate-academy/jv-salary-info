package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String[][] resultData = new String[names.length][2];
        Date to;
        Date from;
        Date tempDate;
        for (int i = 0; i < resultData.length; i++) {
            resultData[i][0] = names[i];
            resultData[i][1] = "0";
        }

        try {
            from = formatter.parse(dateFrom);
            to = formatter.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        for (String datum : data) {
            try {
                tempDate = formatter.parse(datum.split(" ", 4)[0]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if ((tempDate.before(to) || tempDate.compareTo(to) <= 0)
                    && (tempDate.after(from) || tempDate.compareTo(from) >= 0)) {
                for (int i = 0; i < resultData.length; i++) {
                    if (datum.split(" ", 4)[1].equals(resultData[i][0])) {
                        resultData[i][1] = Integer.toString(Integer.parseInt(resultData[i][1])
                                + Integer.parseInt(datum.split(" ", 4)[3])
                                * Integer.parseInt(datum.split(" ", 4)[2]));
                    }
                }
            }
        }

        StringBuilder resultString = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());
        for (int i = 0; i < resultData.length - 1; i++) {
            resultString.append(resultData[i][0] + " - "
                    + resultData[i][1] + System.lineSeparator());
        }
        resultString.append(resultData[resultData.length - 1][0] + " - "
                + resultData[resultData.length - 1][1]);

        return resultString.toString();
    }
}
