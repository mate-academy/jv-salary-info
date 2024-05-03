package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            List<String> reportLines = new ArrayList<>();
            reportLines.add("Report for period " + dateFrom + " - " + dateTo);
            for (String name : names) {
                int earned = 0;
                for (String entry : data) {
                    String[] parts = entry.split(" ");
                    Date entryDate = dateFormat.parse(parts[0]);
                    if (name.equals(parts[1]) && !entryDate.before(fromDate)
                            && !entryDate.after(toDate)) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        earned += hours * rate;
                    }
                }
                reportLines.add(name + " - " + earned);
            }
            return String.join(System.lineSeparator(), reportLines);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }
}
