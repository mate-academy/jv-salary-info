package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateF = strToDate(dateFrom);
        Date dateT = strToDate(dateTo);

        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;

            for (String info : data) {
                String[] employeeInfo = info.split(" ");

                if (name.equals(employeeInfo[1])
                        && strToDate(employeeInfo[0]).getTime() >= dateF.getTime()
                        && strToDate(employeeInfo[0]).getTime() <= dateT.getTime()) {
                    salary += Integer.parseInt(employeeInfo[2]) * Integer.parseInt(employeeInfo[3]);
                }
            }

            builder.append("\n").append(name).append(" - ").append(salary);
        }

        return builder.toString();
    }

    public Date strToDate(String inputData) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(inputData);
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        return date;
    }
}
