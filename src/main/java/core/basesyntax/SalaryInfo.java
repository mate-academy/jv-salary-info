package core.basesyntax;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SalaryInfo {
    String result = "";
    int sum = 0;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] info;
        ArrayList<String[]> sortNames = new ArrayList<>();
        Date from;
        Date actualDate;
        Date endDate;

            for (String name: names) {
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
                if (name.equals(info[1])) {
                    if ((actualDate.after(from) || actualDate.equals(from)) && (actualDate.before(endDate) || actualDate.equals(endDate))) {
                        sum = Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                        String[] str = {name, Integer.toString(sum)};
                        sortNames.add(str);
                    }
                }
            }
                if (sortNames.isEmpty()) {
                    String re = "Report for period " + dateFrom  + " - " + dateTo;
                    for (String name1 : names) {
                        re += System.lineSeparator() + name1 + " - " + "0";
                    }
                    return re;
                }
        }
        ArrayList<String[]> secondArray = new ArrayList<>();
        for (String[] row: sortNames) {
            boolean b = false;
            for (String[] rowUnique: secondArray) {
                if (row[0].equals(rowUnique[0])) {
                    b = true;
                    rowUnique[1] = Integer.toString(Integer.parseInt(rowUnique[1]) + Integer.parseInt(row[1]));
                }
            }
            if (!b) {
                secondArray.add(row);
            }
        }
        String res = "Report for period " + dateFrom + " - " + dateTo;
        for (String[] rows: secondArray) {
              res += System.lineSeparator() + rows[0] + " - " + rows[1];
        }
        return res;
    }
}
