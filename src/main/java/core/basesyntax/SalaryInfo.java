package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String report = "Report for period " + dateFrom + " - " + dateTo;
        for (int i = 0;i < names.length;i++) {
            report += "\n" + names[i] + " - ";
            int totalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String [] oneEntry = data[j].split(" ");
                if (names[i].equals(oneEntry[1]) && (parseDate(oneEntry[0])
                        .compareTo(parseDate(dateFrom))) > 0 && (parseDate(oneEntry[0])
                        .compareTo(parseDate(dateTo))) <= 0) {
                    totalSalary += Integer.parseInt(oneEntry[2]) * Integer.parseInt(oneEntry[3]);
                }
            }
            report += totalSalary;
        }
        return report;
    }

    public Date parseDate(String dateInString) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date result = null;
        try {
            result = format.parse(dateInString);
        } catch (ParseException e) {
            System.out.println(("Wrong parsing from String to Date") + e);
        }
        return result;
    }
}
