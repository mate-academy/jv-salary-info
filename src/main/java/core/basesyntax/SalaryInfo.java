package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SalaryInfo {
    String result = "";
    int sum = 0;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] info;
        String[][] sortNames = new String[data.length][2];
        Date from = new Date();
        Date actualDate = new Date();
        Date endDate = new Date();
        for (int i = 0; i < data.length; i++) {
            info = data[i].split(" ");

            try {
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");
                actualDate= format.parse(info[0]);
                from = format.parse(dateFrom);
                endDate = format.parse(dateTo);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if ((actualDate.after(from) || actualDate.equals(from))&& (actualDate.before(endDate) || actualDate.equals(endDate))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(info[1])) {
                        sum = Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
//                        result = result + names[j] + "-" + sum + ("\n");
                        sortNames[i][0] = names[j];
                        sortNames[i][1] = Integer.toString(sum);

                    }
                }
            }
        }
        System.out.println(Arrays.toString(sortNames));
        return result;
    }
}
