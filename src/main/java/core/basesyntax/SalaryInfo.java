package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaries = new StringBuilder(" Report for period "
                + dateFrom + " - " + dateTo + "\r\n");
        for (int c = 0; c < names.length; c++) {
            int lengthSalery = 0;
            for (int a = 0; a < data.length; a++) {
                String [] splittedData = data[a].split(" ");
                String s = splittedData[0];
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");
                Date docDate = new Date();
                Date normaldateFrom = new Date();
                Date normalDateAfter = new Date();
                try {
                    docDate = format.parse(s);
                    normaldateFrom = format.parse(dateFrom);
                    normalDateAfter = format.parse(dateTo);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (names[c].equals(splittedData[1]) && ((docDate.after(normaldateFrom)
                        && docDate.before(normalDateAfter)) || docDate.equals(normalDateAfter)
                        || docDate.equals(normaldateFrom))) {
                    lengthSalery += Integer.parseInt(splittedData[2])
                            * Integer.parseInt(splittedData[3]);
                }
            }
            salaries.append(names[c] + " - " + lengthSalery + "\r\n");
        }
        return salaries.toString().trim();
    }
}
