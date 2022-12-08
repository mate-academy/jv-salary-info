package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String report = "Report for period " + dateFrom + " - " + dateTo;

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int k = 0; k < data.length; k++) {
                String[] workInfo = data[k].split(" ");
                if (names[i].equals(workInfo[1])) {
                    if (!df.parse(workInfo[0]).before(df.parse(dateFrom))
                            && !df.parse(workInfo[0]).after(df.parse(dateTo))) {
                        salary += Integer.parseInt(workInfo[2]) * Integer.parseInt(workInfo[3]);
                    }
                }
            }
            report += System.lineSeparator() + names[i] + " - " + salary;
        }
        return report;
    }
}
