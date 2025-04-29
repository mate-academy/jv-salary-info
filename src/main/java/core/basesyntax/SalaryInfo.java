package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int PAY_PER_HOUR_INDEX = 3;

    public static String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        Date startDate = parseDate(dateFrom);
        Date endDate = parseDate(dateTo);

        for (String name : names) {
            int salary = calculateSalary(name, data, startDate, endDate);
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }

    private static Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing the date in record: " + date, e);
        }
    }

    private static int calculateSalary(String name,
                                       String[] data,
                                       Date startDate,
                                       Date endDate) {
        int totalSalary = 0;
        for (String record : data) {
            String[] parts = record.split(" ");
            Date workDate = parseDate(parts[DATE_INDEX]);
            if (parts[NAME_INDEX].equals(name)
                    && !workDate.before(startDate)
                    && !workDate.after(endDate)) {
                int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                int payPerHour = Integer.parseInt(parts[PAY_PER_HOUR_INDEX]);
                totalSalary += hoursWorked * payPerHour;
            }
        }
        return totalSalary;
    }

}
