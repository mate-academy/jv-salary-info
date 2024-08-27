package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] parts = datum.split(" ");
                int hours = Integer.parseInt(parts[2]);
                int income = Integer.parseInt(parts[3]);
                if (parts[1].equals(name) && dateComparator(parts[0], dateFrom, dateTo)) {
                    salary += hours * income;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return String.valueOf(result);
    }

    public boolean dateComparator(String data, String dataFrom, String dataTo) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = format.parse(data);
            Date dateFrom = format.parse(dataFrom);
            Date dateTo = format.parse(dataTo);
            if (date.after(dateFrom) && date.before(dateTo)
                    || date.equals(dateFrom) || date.equals(dateTo)) {
                return true;
            }
        } catch (ParseException ex) {
            System.out.println("Problem with data" + ex);
        }
        return false;
    }
}
