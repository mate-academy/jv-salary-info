package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period " + dateFrom + " - " + dateTo);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate = null;
        Date toDate = null;
        Date currentDate = null;
        try {
            fromDate = formatter.parse(dateFrom);
            toDate = formatter.parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid data format";
        }
        
        for (String name: names) {
            int salaryPerPerson = 0;
            for (String record: data) {
                String[] splittedRecord = record.split(" ");
                try {
                    currentDate = formatter.parse(splittedRecord[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }
                if ((currentDate.equals(fromDate) || currentDate.after(fromDate)) &&
                    (currentDate.equals(toDate) || currentDate.before(toDate)) &&
                    name.equals(splittedRecord[1])) {
                    salaryPerPerson += (Integer.valueOf(splittedRecord[2]) * Integer.valueOf(splittedRecord[3]));
                }
            }
            builder.append("\n" + name + " - " + salaryPerPerson);
        }
        return builder.toString();
    }
}
