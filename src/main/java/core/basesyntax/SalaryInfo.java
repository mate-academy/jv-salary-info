package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateFromDate = new Date();
        Date dateToDate = new Date();
        Date date = new Date();
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        try {
            dateFromDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
        } catch (ParseException e) {
            System.out.println("Date from parsed error: " + dateFrom);
        }
        try {
            dateToDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            System.out.println("Date to parsed error: " + dateTo);
        }
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int totalSallery = 0;
            for (String dataRow : data) {
                if (dataRow.isEmpty()) {
                    continue;
                }
                String[] dataRowArray = dataRow.split(" ");
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(dataRowArray[0]);
                } catch (ParseException e) {
                    System.out.println("Date parsed error: " + dataRowArray[0]);
                }
                if ((date.after(dateFromDate) || date.equals(dateFromDate))
                        && (date.before(dateToDate) || date.equals(dateToDate))
                        && dataRowArray[1].equals(name)) {
                    totalSallery += Integer.parseInt(dataRowArray[2]) * Integer.parseInt(dataRowArray[3]);
                }
            }
            builder.append(totalSallery);
        }
        return builder.toString();
    }
}
