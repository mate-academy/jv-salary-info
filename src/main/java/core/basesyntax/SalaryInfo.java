package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder periodData = new StringBuilder();
        try {
            Date fromDate = simpleDateFormat.parse(dateFrom);
            Date toDate = simpleDateFormat.parse(dateTo);
            for (String day : data) {
                String[] string = day.split(" ");
                Date date = simpleDateFormat.parse(string[0]);
                if ((date.after(fromDate) || fromDate.equals(date))
                        && (date.before(toDate) || date.equals(toDate))) {
                    periodData.append(day).append(",");
                }
            }
        } catch (ParseException e) {
            System.out.println("Can't parse date");
        }
        String[] periodDataArr = periodData.toString().split(",");
        int[] salsry = new int[names.length];
        StringBuilder builder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (periodDataArr.length > 1) {
                for (String personDataStr : periodDataArr) {
                    String[] dataPerson = personDataStr.split(" ");
                    if (dataPerson[1].equals(names[i])) {
                        salsry[i] += Integer.parseInt(dataPerson[2]) * Integer.parseInt(dataPerson[3]);
                    }
                }
            }
            if (i < names.length - 1) {
                builder.append(names[i]).append(" - ").append(salsry[i]).append(System.lineSeparator());
            } else {
                builder.append(names[i]).append(" - ").append(salsry[i]);
            }
        }
        return builder.toString();
    }
}
