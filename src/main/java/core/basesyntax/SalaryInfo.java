package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder res = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        Date dateF = null;
        Date dateT = null;
        try {
            dateF = (Date) formatter.parse(dateFrom);
            dateT = (Date) formatter.parse(dateTo);

        } catch (ParseException e) {
            //
            e.printStackTrace();
        }
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int fullSalary = 0;
            for (int j = 0; j < data.length; j++) {
                DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
                StringBuilder string = new StringBuilder(data[j]);
                if (string.length() > 8 || data[j] != null) {
                    String dateString = string.substring(0,string.indexOf(" "));
                    try {
                        date = (Date) formatter.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int fr = string.indexOf(" ") + 1;
                    int to = fr + string.substring(string.indexOf(" ") + 2).indexOf(" ") + 1;
                    String checkedName = string.substring(fr,to);
                    int hours = Integer.parseInt(string.substring(to + 1,string.lastIndexOf(" ")));
                    int salaryPerHour = Integer.parseInt(
                            string.substring(string.lastIndexOf(" ") + 1));
                    int salary = hours * salaryPerHour;
                    if (checkedName.equals(name) && !date.before(dateF) && !date.after(dateT)) {
                        fullSalary = salary + fullSalary;
                    }
                    if (j == data.length - 1) {
                        StringBuilder resName = new StringBuilder(name + " - " + fullSalary);
                        res.append(System.lineSeparator());
                        res.append(resName);
                    }
                }
            }
        }
        return res.toString();
    }
}
