package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final String REPORT_TITLE = "Report for period ";
    private static final String DATE_DELIM = " - ";
    private static final String DATE_SPLIT = " ";
    private static final int DATE_IN_DATA = 0;
    private static final int NAME_IN_DATA = 1;
    private static final int HOURS_IN_DATA = 2;
    private static final int SALARY_PER_HOURS_IN_DATA = 3;
    private static final int DATE_LINE_SIZE = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append(REPORT_TITLE)
                .append(dateFrom)
                .append(DATE_DELIM)
                .append(dateTo)
                .append(System.lineSeparator());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate;
        Date toDate;
        try {
            fromDate = dateFormat.parse(dateFrom);
            toDate = dateFormat.parse(dateTo);
        } catch (ParseException ex) {
            throw new RuntimeException("Invalid data format!", ex);
        }
        int counter = 0;
        for (String name : names) {
            int totalSalary = 0;
            for (String entry : data) {
                String[] entryParts = entry.split(DATE_SPLIT);
                if (entryParts.length != DATE_LINE_SIZE) {
                    throw new RuntimeException("Invalid data format!");
                }
                try {
                    Date entryDate = dateFormat.parse(entryParts[DATE_IN_DATA]);
                    if (entryParts[NAME_IN_DATA].equals(name)
                            && isWithinRange(entryDate, fromDate, toDate)) {
                        int hoursWorked = Integer.parseInt(entryParts[HOURS_IN_DATA]);
                        int incomePerHour = Integer.parseInt(entryParts[SALARY_PER_HOURS_IN_DATA]);
                        totalSalary += hoursWorked * incomePerHour;
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException("Invalid data format!", ex);
                }
            }
            report.append(name)
                    .append(DATE_DELIM)
                    .append(totalSalary);
            if (counter < names.length - 1) {
                report.append(System.lineSeparator());
            }
            counter++;
        }
        return report.toString();
    }

    private static boolean isWithinRange(Date date, Date fromDate, Date toDate) {
        return !(date.before(fromDate) || date.after(toDate));
    }
}
