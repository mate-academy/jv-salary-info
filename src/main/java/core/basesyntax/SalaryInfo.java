package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws ParseException {

        int[] salary = new int[names.length];

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date date = new Date();

        Date startDate = format.parse(dateFrom);

        Date endDate = format.parse(dateTo);

        String finalResult = "Report for period "
                + dateFrom + " - "
                + dateTo
                + System.lineSeparator();;

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])
                        & (format.parse(data[j].split(" ")[0]).before(endDate)
                        | format.parse(data[j].split(" ")[0]).equals(endDate))
                        & (format.parse(data[j].split(" ")[0]).after(startDate)
                        | format.parse(data[j].split(" ")[0]).equals(startDate))) {
                    int incomePH = Integer.parseInt(
                            data[j].substring(data[j].lastIndexOf(" ") + 1));
                    String hoursSplit = data[j].substring(0,data[j].lastIndexOf(" "));
                    int hours = Integer.parseInt(
                            hoursSplit.substring(hoursSplit.lastIndexOf(" ") + 1));
                    salary[i] = salary[i] + incomePH * hours;
                }
            }
            if (i < names.length - 1) {
                finalResult = finalResult + names[i] + " - " + salary[i] + System.lineSeparator();
            } else {
                finalResult = finalResult + names[i] + " - " + salary[i];
            }
        }
        return finalResult;
    }
}
