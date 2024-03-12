package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        int count = 0;
        for (String name : names) {
            int sum = 0;
            for (String elementOfData : data) {
                String[] elements = elementOfData.split(" ");
                if (elements[1].equals(name)) {
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    Date docDate = null;
                    Date fromDate = null;
                    Date toDate = null;
                    try {
                        docDate = format.parse(elements[0]);
                        fromDate = format.parse(dateFrom);
                        toDate = format.parse(dateTo);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fromDate);
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                        fromDate = calendar.getTime();
                        calendar.setTime(toDate);
                        calendar.add(Calendar.DAY_OF_MONTH, +1);
                        toDate = calendar.getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (docDate.after(fromDate) && docDate.before(toDate)) {
                        sum += Integer.parseInt(elements[2]) * Integer.parseInt(elements[3]);
                    }
                }
            }
            if (count == names.length - 1) {
                result.append(name).append(" - ").append(sum);
            } else {
                result.append(name).append(" - ").append(sum).append("\n");
                count++;
            }
        }
        return result.toString();
    }
}
