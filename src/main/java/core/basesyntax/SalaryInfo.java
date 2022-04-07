package core.basesyntax;



import java.text.SimpleDateFormat;
import java.time.DateTimeException;

import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String result;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy" );
            Date startDate = dateFormat.parse(dateFrom);
            Date endDate = dateFormat.parse(dateTo);
            Date salaryDate = dateFormat.parse(dateFrom);
            System.out.println("Report for period " + dateFormat.format(startDate) + " - " + dateFormat.format(endDate));
        }
        catch (DateTimeException exc) {
            System.out.printf("%s Date is wrong formatted!%n");
            throw exc;
        }
        if (dateFrom != null && dateTo != null) {
            for (String name : names) {
                for (String dataStr : data) {


                }
            }

            return result;
        } else {
            return null;
        }
    }
}

