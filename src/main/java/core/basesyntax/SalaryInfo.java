package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_ELEMENT = 0;
    private static final int NAME_ELEMENT = 1;
    private static final int HOURS_ELEMENT = 2;
    private static final int INCOME_ELEMENT = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null) {
            throw new IllegalArgumentException("Empty array(s)");
        }
        if (dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Empty string(s)");
        }

        Date firstDay = null;
        try {
            firstDay = DATE_FORMAT.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date lastDay = null;
        try {
            lastDay = DATE_FORMAT.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (String name : names) {
            int salary = 0;

            try {
                salary = calculateEmployeeSalary(data, name, firstDay, lastDay);
            } catch (Exception e) {
                e.printStackTrace();
            }

            report.append(name)
                    .append(" - ")
                    .append(salary)
                    .append("\n");
        }
        return report.substring(0, report.length() - 1);
    }

    public int calculateEmployeeSalary(String[] data, String employeeName, Date firstDay,
                                       Date lastDay) {
        int salary = 0;

        for (String info : data) {
            String[] employeeInfo = info.split(" ");
            Date date = null;

            try {
                date = (Date) DATE_FORMAT.parse(employeeInfo[DATE_ELEMENT]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date.before(firstDay) || date.after(lastDay)) {
                continue;
            }

            if (employeeInfo[NAME_ELEMENT].equals(employeeName)) {
                salary += Integer.parseInt(employeeInfo[HOURS_ELEMENT])
                        * Integer.parseInt(employeeInfo[INCOME_ELEMENT]);
            }
        }
        return salary;
    }
}
