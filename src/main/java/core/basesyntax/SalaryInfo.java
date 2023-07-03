package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] parts = data[i].split(" ");
                if (isWithinRange(parts[0], dateFrom, dateTo) && name.equals(parts[1])) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    salary += hours * rate;
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return sb.toString();
    }

    private static boolean isWithinRange(String date, String dateFrom, String dateTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date d = sdf.parse(date);
            Date from = sdf.parse(dateFrom);
            Date to = sdf.parse(dateTo);
            return d.compareTo(from) >= 0 && d.compareTo(to) <= 0;
        } catch (ParseException e) {
            System.out.println(e);
        }
        return false;
    }
}
