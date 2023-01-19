package core.basesyntax;

import java.util.Calendar;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder =
                new StringBuilder("Report for period" + " " + dateFrom + " - " + dateTo);
        int[] datefromint = info(dateFrom);
        int[] datetoint = info(dateTo);
        Calendar calendarmin = Calendar.getInstance();
        calendarmin.set(datefromint[2], datefromint[1], datefromint[0]);
        Calendar calendarmax = Calendar.getInstance();
        calendarmax.set(datetoint[2], datetoint[1], datetoint[0]);
        calendarmax.add(Calendar.DATE, 1);
        String[] datawithsplit = new String[4];
        int[] datadate = new int[3];
        Calendar calendarX = Calendar.getInstance();
        int startbalance = 0;

        for (String name : names) {
            startbalance = 0;
            for (String datas : data) {
                datawithsplit = datas.split(" ");
                if (datawithsplit[1].equals(name)) {
                    datadate = info(datawithsplit[0]);
                    calendarX.set(datadate[2], datadate[1], datadate[0]);
                    if (calendarX.after(calendarmin) && calendarX.before(calendarmax)){
                        startbalance = startbalance + Integer.parseInt(datawithsplit[2])
                                * Integer.parseInt(datawithsplit[3]);
                    }
                }
            }
            builder.append("\n").append(name).append(" - ").append(startbalance);
        }
        return builder.toString();
    }

    public int[] info(String array) {
        String[] datefrom = array.split("\\.");
        int[] result = new int[3];

        for (int i = 0; i < 3; i++) {
            result[i] = Integer.parseInt(datefrom[i]);
        }
        return result;
    }
}
