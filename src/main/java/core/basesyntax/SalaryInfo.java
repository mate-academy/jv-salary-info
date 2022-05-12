package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");
        int sum;
        for (String name: names) {
            sum = 0;
            for (String dataStr: data) {
                String[] dataArray = dataStr.split(" ");
                if (name.equals(dataArray[1]) && checkDate(dataArray[0], dateFrom, dateTo)) {
                    sum += getDaySalary(dataArray[2], dataArray[3]);
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(sum)
                    .append("\n");
        }
        return builder.toString().trim();
    }

    public boolean checkDate(String date, String dateFrom, String dateTo) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return (format.parse(date).after(format.parse(dateFrom))
                    || format.parse(date).equals(format.parse(dateFrom)))
                    && (format.parse(date).before(format.parse(dateTo))
                    || format.parse(date).equals(format.parse(dateTo)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDaySalary(String hours, String sph) {
        return Integer.parseInt(hours) * Integer.parseInt(sph);
    }
}
