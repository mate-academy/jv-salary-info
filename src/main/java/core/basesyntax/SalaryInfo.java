package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom + " - " + dateTo);

        for (String name : names) {
            int sum = 0;
            for (String information : data) {
                String[] parseData = information.split(" ");
                if (checkDate(dateFrom, dateTo, parseData[0]) && parseData[1].equals(name)) {
                    sum += Integer.parseInt(parseData[2]) * Integer.parseInt(parseData[3]);
                }
            }
            result.append("\n" + name).append(" - " + sum);
        }
        return result.toString();
    }

    private boolean checkDate(String dateFrom, String dateTo, String date) {
        Date startDate = null;
        Date finalDate = null;
        Date localDate = null;
        try {
            startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            finalDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            localDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);

        } catch (ParseException e) {
            System.out.println("Wrong date format!");
        }
        if ((localDate.after(startDate) && localDate.before(finalDate))
                || localDate.equals(finalDate)) {
            return true;
        }
        return false;
    }
}
