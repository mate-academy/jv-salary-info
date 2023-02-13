package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyy";
    private static final short LINE_DATE = 0;
    private static final short LINE_NAME = 1;
    private static final short LINE_HOURS = 2;
    private static final short LINE_SALARY = 3;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            report.append(name).append(" - ")
                    .append(getSalary(name, data, dateFrom, dateTo)).append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private int getSalary(String name, String[] data, String fromDate, String toDate) {
        int salary = 0;
        String[] table;
        for (String tableLine : data) {
            table = tableLine.trim().split(" ");
            if ((name.equals(table[LINE_NAME]))
                    && (isSuitableDate(table[LINE_DATE], fromDate, toDate))) {
                salary += Integer.decode(table[LINE_HOURS]) * Integer.decode(table[LINE_SALARY]);
            }
        }
        return salary;
    }

    private boolean isSuitableDate(String lineString, String fromString, String toString) {
        Date lineDate;
        Date fromDate;
        Date toDate;
        try {
            lineDate = simpleDateFormat.parse(lineString);
            fromDate = simpleDateFormat.parse(fromString.trim());
            toDate = simpleDateFormat.parse(toString.trim());
        } catch (ParseException e) {
            System.out.println("Date parse error!");
            return false;
        }
        return ((lineDate.getTime() >= fromDate.getTime())
                && (lineDate.getTime() <= toDate.getTime()));
    }
}
