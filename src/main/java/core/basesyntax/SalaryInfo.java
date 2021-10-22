package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder result = new StringBuilder("Report for period ");
        Date startDate = null;
        Date finishDate = null;
        try {
            startDate = format.parse(dateFrom);
            finishDate = format.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finishDate = new Date(finishDate.getTime() + 86400000);
        result.append(dateFrom).append(" - ").append(dateTo).append("\r\n");
        for (String name : names) {
            int salary = 0;
            result.append(name).append(" - ");
            for (String line : data) {
                Date nowDate = startDate;
                while (nowDate.before(finishDate)) {
                    String startDateString = format.format(nowDate);
                    salary += getSalary(line, startDateString,name);
                    nowDate = new Date(nowDate.getTime() + 86400000);
                }
            }
            result.append(salary).append("\r\n");
        }
        result.setLength(result.length() - 2);
        return result.toString();
    }

    private int getSalary(String line, String date, String name) {
        if ((line.contains(date)) && (line.contains(name))) {
            int startIndexWorkingHour = line.indexOf(name) + name.length() + 1;
            int workingHour = Integer.parseInt(line.substring(startIndexWorkingHour,
                    line.lastIndexOf(" ")));
            int money = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1, line.length()));
            return workingHour * money;
        }
        return 0;
    }
}



