package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        Date startDate;
        Date endDate;
        try {
            startDate = parseDate(dateFrom);
            endDate = parseDate(dateTo);
        } catch (ParseException e) {
            return "Error: Invalid date format";
        }

        for (String name : names) {
            int salary = calculateSalary(name, data, startDate, endDate);
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return report.toString();
    }

    private static Date parseDate(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }

    private static int calculateSalary(String name,
                                       String[] data,
                                       Date startDate,
                                       Date endDate) {
        int totalSalary = 0;
        for (String record : data) {
            String[] parts = record.split(" ");
            try {
                Date workDate = DATE_FORMAT.parse(parts[0]);
                if (parts[1].equals(name)
                        && !workDate.before(startDate)
                        && !workDate.after(endDate)) {
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int payPerHour = Integer.parseInt(parts[3]);
                    totalSalary += hoursWorked * payPerHour;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return totalSalary;
    }
}
