package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        int salary;
        Date dateFromD;
        Date dateToD;
        Date dateSalary;
        try {
            dateFromD = formatter.parse(dateFrom);
            dateToD = formatter.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (String name : names) {
            stringBuilder.append(System.lineSeparator());
            salary = 0;
            for (String dateStr : data) {
                String[] dateArr = dateStr.split(" ");
                try {
                    dateSalary = formatter.parse(dateArr[0]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (name.equals(dateArr[1]) && dateFromD.getTime() <= dateSalary.getTime()
                        && dateToD.getTime() >= dateSalary.getTime()) {
                    salary += Integer.parseInt(dateArr[2]) * Integer.parseInt(dateArr[3]);
                }
            }
            stringBuilder.append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
