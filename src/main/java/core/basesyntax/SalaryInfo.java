package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom + " - ").append(dateTo);
        Date dateFromD;
        Date dateToD;
        try {
            dateFromD = formatter.parse(dateFrom);
            dateToD = formatter.parse(dateTo);
            int[] salaries = new int[names.length];
            for (int i = 0; i < data.length; i++) {
                String[] dataForOnePeople = data[i].split(" ");
                if (formatter.parse(dataForOnePeople[0]).compareTo(dateFromD) >= 0
                        && formatter.parse(dataForOnePeople[0]).compareTo(dateToD) <= 0) {
                    for (int j = 0; j < names.length; j++) {
                        if (dataForOnePeople[1].equals(names[j])) {
                            salaries[j] += Integer.parseInt(dataForOnePeople[2])
                                    * Integer.parseInt(dataForOnePeople[3]);
                        }
                    }
                }
            }
            for (int i = 0; i < names.length; i++) {
                builder.append(System.lineSeparator()).append(names[i]).append(" - ")
                        .append(salaries[i]);
            }
        } catch (ParseException e) {
            return null;
        }
        return builder.toString();
    }
}
