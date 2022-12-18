package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws RuntimeException {
        int result;
        String summaryString = "Report for period " + dateFrom + " - " + dateTo;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        Date dateStart = null;
        try {
            dateStart = formatter.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date dateEnd = null;
        try {
            dateEnd = formatter.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //System.out.println("DateFrom: " + dateStart + "\n" + "DateTo: " + dateEnd);

        for (int i = 0; i < names.length; i++) {
            result = 0;
            for (String s : data) {
                String[] strArray = s.split(" ");
                //System.out.println(Arrays.toString(strArray));
                Date dateCurrent;
                try {
                    dateCurrent = formatter.parse(strArray[0]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                if (strArray[1].equals(names[i])
                        && ((dateCurrent.compareTo(dateEnd) == 0
                                || dateCurrent.compareTo(dateEnd) == -1))
                        && (dateCurrent.compareTo(dateStart) == 0
                                || dateCurrent.compareTo(dateStart) == 1)) {
                    result += Integer.parseInt(strArray[2])
                            * Integer.parseInt(strArray[3]);
                }
            }
            summaryString += System.lineSeparator() + names[i] + " - " + result;
        }
        //System.out.println(Arrays.toString(names));
        return summaryString;
    }
}
