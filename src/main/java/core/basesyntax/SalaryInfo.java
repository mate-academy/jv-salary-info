package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date from;
        Date to;
        try {
            from = simpleDateFormat.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format [" + dateFrom + "]", e);
        }
        try {
            to = simpleDateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format [" + dateTo + "]", e);
        }
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            result.append(names[i]);
            result.append(" - ");
            for (String record: data) {
                String [] dates = record.split(" ");
                try {
                    Date current = simpleDateFormat.parse(dates[0]);
                    if (current.compareTo(from) != -1 && current.compareTo(to) != 1
                            && dates[1].equals(names[i])) {
                        int hours = Integer.parseInt(dates[2]);
                        int cost = Integer.parseInt(dates[3]);
                        salary += hours * cost;
                    }
                } catch (ParseException e) {
                    throw new RuntimeException("Invalid date format [" + dates[0] + "]", e);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number format hours or cost", e);
                }

            }
            result.append(salary);
            if (i != names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
