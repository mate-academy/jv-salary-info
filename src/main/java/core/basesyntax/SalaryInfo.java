package core.basesyntax;

import java.time.LocalTime;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ");
            String [] elements = data[i].split( " ");
            Date date = new Date(elements[0]);
            Date date1 = new Date(dateFrom);
            Date date2 = new Date(dateTo);
            if (date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0) {
                stringBuilder.append(Integer.valueOf(data[2]) * Integer.valueOf(data[3]));
            }
        }
        return stringBuilder.toString();
    }
}
