package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] countData = new String[data.length];
        int[] wages = new int[names.length];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate;
        try {
            fromDate = simpleDateFormat.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date toDate;
        try {
            toDate = simpleDateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date compareDate;
        String withoutName;
        int hours;
        int money;
        int wage;
        int i = 0;
        for (String date : data) {
            try {
                compareDate = simpleDateFormat.parse(date.substring(0, 10));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (fromDate.getTime() <= compareDate.getTime()
                    && compareDate.getTime() <= toDate.getTime()) {
                countData[i] = date.substring(10);
                i++;
            }
        }
        i = 0;
        for (String s : countData) {
            for (String name : names) {
                if (s.contains(name)) {
                    withoutName = s.substring(name.length()).trim();
                    hours = Integer.parseInt(withoutName.substring(0, withoutName.indexOf(' ')));
                    money = Integer.parseInt(withoutName
                            .substring(withoutName.indexOf(' ')).trim());
                    wage = hours * money;
                    wages[i] = wage;
                    i++;
                }
            }
        }
        StringBuilder output = new StringBuilder();
        output.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append('\n');
        for (int j = 0; j < names.length; j++) {
            output.append(names[j]).append(" - ").append(wages[j]).append('\n');
        }
        return output.toString();
    }
}
