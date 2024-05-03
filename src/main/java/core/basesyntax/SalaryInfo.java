package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOUR_INDEX = 2;
    static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            List<String> reportLines = new ArrayList<>();
            reportLines.add("Report for period " + dateFrom + " - " + dateTo);
            for (String name : names) {
                int earned = calculateEarnedSalary(name, data, fromDate, toDate, dateFormat);
                reportLines.add(name + " - " + earned);
            }
            return String.join(System.lineSeparator(), reportLines);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }

    private int calculateEarnedSalary(String name, String[] data,
                                      Date fromDate, Date toDate,
                                      SimpleDateFormat dateFormat) throws ParseException {
        int earned = 0;
        for (String entry : data) {
            String[] parts = entry.split(" ");
            Date entryDate = dateFormat.parse(parts[DATE_INDEX]);
            if (isWithinDateRange(name, entryDate, fromDate, toDate, parts)) {
                int hours = Integer.parseInt(parts[HOUR_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);
                earned += hours * rate;
            }
        }
        return earned;
    }

    private boolean isWithinDateRange(String name, Date entryDate, Date fromDate,
                                      Date toDate, String[] parts) {
        return name.equals(parts[NAME_INDEX])
                && !entryDate.before(fromDate)
                && !entryDate.after(toDate);
    }
}
