package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public boolean inPeriod(String date, String dateFrom, String dateTo) {
        try {
            Date checkDate = simpleDateFormat.parse(date);
            Date dateStart = simpleDateFormat.parse(dateFrom);
            Date dateEnd = simpleDateFormat.parse(dateTo);
            if ((checkDate.after(dateStart)
                    && checkDate.before(dateEnd))
                    || checkDate.equals(dateStart)
                    || checkDate.equals(dateEnd)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            throw new RuntimeException("Error of parsing string to date!");
        }
    }

    public int nameIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                return i;
            }
        }
        return -1;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String result = "Report for period " + dateFrom + " - " + dateTo;
        int[] sumArray = new int[names.length];
        String[] row = new String[4];
        for (String dataRow : data) {
            row = dataRow.split(" ");
            if (inPeriod(row[0], dateFrom, dateTo)) {
                sumArray[nameIndex(names, row[1])] += Integer.parseInt(row[2])
                        * Integer.parseInt(row[3]);
            }
        }
        for (int i = 0; i < names.length; i++) {
            result += System.lineSeparator() + names[i] + " - " + sumArray[i];
        }
        return result;
    }
}
