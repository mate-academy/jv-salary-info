package core.basesyntax;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ");
        String [] date1 = dateFrom.split(" ");
        String [] date2 = dateTo.split(" ");

        String startDate = date1[date1.length - 1].substring (1, date1[date1.length - 1].length () -1);
        startDate = startDate.replace('.','-');
        SimpleDateFormat formatter = new SimpleDateFormat ("dd-M-yyyy", Locale.ENGLISH);
        Date startDate1 = formatter.parse(startDate);
        stringBuilder.append(startDate);
        stringBuilder.append(" - ");

        String endDate = date2[date2.length - 1].substring (1, date2[date2.length - 1].length () -1);
        endDate = endDate.replace('.','-');
        Date endDate1 = formatter.parse(endDate);
        stringBuilder.append(endDate);
        stringBuilder.append("\n");

        for (int i = 0; i < names.length; i++) {
            int money = 0;
            for(int j = 0; j < data.length; j++){
                String [] elements = data[j].split( " ");
                String workDate = elements[0];
                workDate = workDate.replace('.','-');
                Date workDate1 = formatter.parse(workDate);
                if (workDate1.compareTo(startDate1) >= 0 && workDate1.compareTo(endDate1) <= 0) {
                    System.out.println ("Weszlo! - Date jest w tym okresie - " + workDate1);
                    int value = Integer.parseInt(elements[2]) * Integer.parseInt(elements[3]);
                    System.out.println(value);
                    if (names[i].equals(elements[1])) {
                        System.out.println((names[i] + " to " + (elements[1])));
                        money += value;
                    }
                }
            }
            stringBuilder.append(names[i]).append(" - ").append(String.valueOf(money));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
