package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            builder.append(System.lineSeparator());
            int salary = 0;
            for (String oneString: data) {
                String[] dataFormat = oneString.split(" ");
                try {
                    Date tempDate = FORMAT.parse(dataFormat[0]);
                    Date begin = FORMAT.parse(dateFrom);
                    Date end = FORMAT.parse(dateTo);
                    if ((name.equals(dataFormat[1])
                            && ((tempDate).after(begin) && tempDate.before(end)
                            || (begin.equals(tempDate) || tempDate.equals(end))))) {
                        salary += Integer.parseInt(dataFormat[2]) * Integer.parseInt(dataFormat[3]);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException("Could not parse date", e);
                }
            }
            builder.append(name).append(" - ").append(salary);
        }
        return builder.insert(0, "Report for period " + dateFrom + " - " + dateTo).toString();
    }
}
