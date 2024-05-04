package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOUR_INDEX = 2;
    static final int RATE_INDEX = 3;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        dateFormat.setLenient(false);
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            StringBuilder reportLines = new StringBuilder();
            reportLines.append("Report for period ").append(dateFrom).append(" - ")
                    .append(dateTo).append(System.lineSeparator());
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                int earned = calculateEarnedSalary(name, data, fromDate, toDate, dateFormat);
                reportLines.append(name).append(" - ")
                        .append(earned);
                if (i < names.length - 1) {
                    reportLines.append(System.lineSeparator());
                }
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
