package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date fromDate = null;
        Date toDate = null;
        String result = "Report for period " + dateFrom + " - " + dateTo;
        fromDate = getDate(dateFrom);
        toDate = getDate(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String str : data) {
                String[] split = str.split(" ");
                Date date = getDate(split[0]);
                if (date.getTime() >= fromDate.getTime() && date.getTime() <= toDate.getTime()) {
                    if (name.equals(split[1])) {
                        salary += Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                    }
                }
            }
            result += System.lineSeparator() + name + " - " + salary;
        }
        return result;
    }

    private Date getDate(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            System.out.println("date not in valid format");
            throw new RuntimeException(e);
        }
        return date;
    }
}
