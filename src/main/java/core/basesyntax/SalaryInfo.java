package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static SimpleDateFormat format = new SimpleDateFormat();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        format.applyPattern("dd.MM.yyyy");
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(dateFrom);
            endDate = format.parse(dateTo);
        } catch (ParseException e) {
            return "Format dates is unknown";
        }
        Date currentDate = null;

        for (String name: names) {
            int sum = 0;
            for (String dateLine: data) {
                if (dateLine.contains(name)) {
                    String[] record = dateLine.split(" ");
                    try {
                        currentDate = format.parse(record[0]);
                    } catch (ParseException e) {
                        return "Format data record is unknown";
                    }
                    if (currentDate.compareTo(beginDate) == 0 || currentDate.compareTo(endDate) == 0
                            || (currentDate.after(beginDate) && currentDate.before(endDate))) {
                        sum += Integer.parseInt(record[2]) * Integer.parseInt(record[3]);
                    }
                }

            }
            sb.append("\r\n").append(name).append(" - ").append(sum);
        }
        return sb.toString();
    }
}
