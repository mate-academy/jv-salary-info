package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int sum;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ");
        sb.append(dateFrom);
        sb.append(" - ");
        sb.append(dateTo);
        for (String name : names) {
            sb.append(System.lineSeparator());
            sb.append(name);
            sb.append(" - ");
            sum = 0;
            for (String line : data) {
                if (line != null && line.length() > 0 && line.contains(name)) {
                    String[] s = line.split(" ");
                    try {
                        Date lineDate = formatter.parse(s[0]);
                        Date fromDate = formatter.parse(dateFrom);
                        Date toDate = formatter.parse(dateTo);
                        if (lineDate.compareTo(fromDate) >= 0 && lineDate.compareTo(toDate) <= 0
                                && name.equals(s[1])) {
                            sum += Integer.parseInt(s[2]) * Integer.parseInt(s[3]);
                        }
                    } catch (ParseException e) {
                        System.out.println("Bad date input");
                    }
                }
            }
            sb.append(sum);
        }
        return sb.toString();
    }
}
