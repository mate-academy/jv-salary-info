package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        ArrayList<String> dataList = new ArrayList<>(Arrays.asList(data));
        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.y");

        try {
            Date dateFrom_Data = formatter.parse(dateFrom);
            Date dateTo_Data = formatter.parse(dateTo);

            for (int i = 0; i < dataList.size(); i++) {
                if (dateFrom_Data.after(formatter.parse(dataList.get(i).substring(0,
                        dataList.get(i).indexOf(' ')))) || dateTo_Data.before(formatter.parse(
                                dataList.get(i).substring(0, dataList.get(i).indexOf(' '))))) {
                    dataList.remove(i);
                    i--;
                } else {
                    dataList.set(i, dataList.get(i).substring(dataList.get(i).indexOf(' ') + 1));
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException("Sorry, unparseable date");
        }
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < dataList.size(); j++) {
                if (names[i].equals(dataList.get(j).substring(0, dataList.get(j).indexOf(' ')))) {
                    dataList.set(j, dataList.get(j).substring(dataList.get(j).indexOf(' ') + 1));
                    salaries[i] += Integer.parseInt(dataList.get(j).substring(
                            0, dataList.get(j).indexOf(' '))) * Integer.parseInt(
                                    dataList.get(j).substring(dataList.get(j).indexOf(' ') + 1));
                }
            }
        }
        StringBuilder answer = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            answer.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaries[i]);
        }
        return answer.toString();
    }
}
