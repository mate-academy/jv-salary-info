package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        String result = "";
        try {
            long from = simpleDateFormat.parse(dateFrom).getTime();
            long to = simpleDateFormat.parse(dateTo).getTime();

            for (String name : names) {
                int salary = 0;
                for (String d : data) {
                    String[] dd = d.split(" ");
                    if (dd[1].equals(name) && simpleDateFormat.parse(dd[0]).getTime() >= from
                            && simpleDateFormat.parse(dd[0]).getTime() <= to) {
                        salary += (Integer.valueOf(dd[2]) * Integer.valueOf(dd[3]));
                    }
                }
                result += name + " - " + salary + "\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Report for period " + dateFrom + " - " + dateTo + "\n" + result.trim();
    }
}
