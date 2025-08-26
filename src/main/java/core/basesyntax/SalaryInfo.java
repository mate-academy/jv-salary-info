package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date start = null;
        Date end = null;
        try {
            start = format.parse(dateFrom);
            end = format.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (String datum : data) {
            String[] separator = datum.split("\\s+");
            try {
                Date current = format.parse(separator[0]);

                if (!current.before(start) && !current.after(end)) {
                    for (int j = 0; j < names.length; j++) {
                        if (separator[1].equals(names[j])) {
                            salary[j] += Integer.parseInt(separator[2])
                                    * Integer.parseInt(separator[3]);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("\n")
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append("\n")
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }

        return report.toString();
    }
}
