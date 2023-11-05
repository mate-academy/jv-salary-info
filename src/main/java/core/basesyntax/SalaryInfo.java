package core.basesyntax;
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
     ;
        ArrayList<String[]> sortNames = new ArrayList<>();
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
            if ((actualDate.after(from) || actualDate.equals(from)) && (actualDate.before(endDate) || actualDate.equals(endDate))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(info[1])) {
                        sum = Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
//                        result = result + names[j] + "-" + sum + ("\n");
                        String[] str = {names[j],Integer.toString(sum), info[0]};
                        sortNames.add(str);
                    }
                }
            }
        }
        System.out.println(sortNames);
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
        String res = "Report for period " + dateFrom + " - " + dateTo + "\n";
        for (String[] rows: secondArray) {
              res += rows[0] + " - " + rows[1] + "\n";
        }
        Comparator<String[]> comparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] arr1, String[] arr2) {
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");
                actualDate= format.parse(info[0]);
                return actualDate.equals(actualDate);
            }
        };
        Collections.sort(sortNames, comparator);
        System.out.println(secondArray);

        System.out.println(res);
        return res;
    }
}
