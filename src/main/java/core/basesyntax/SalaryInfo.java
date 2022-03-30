package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static Date FIRST_DATE;
    private static Date SECOND_DATE;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            FIRST_DATE = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            SECOND_DATE = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Could not parse", e);
        }
        StringBuilder builder = new StringBuilder();
        try {
            for (String name: names) {
                builder.append(System.lineSeparator());
                int salary = 0;
                for (int i = 0; i < data.length; i++) {
                    String[] dataFormat = data[i].split(" ");
                    Date tempDate = new SimpleDateFormat("dd.MM.yyyy").parse(dataFormat[0]);
                    if ((name.equals(dataFormat[1])
                            && tempDate.after(FIRST_DATE) && tempDate.before(SECOND_DATE))
                            || (name.equals(dataFormat[1])
                            && (FIRST_DATE.equals(tempDate) || SECOND_DATE.equals(tempDate)))) {
                        salary += Integer.parseInt(dataFormat[2]) * Integer.parseInt(dataFormat[3]);
                    }
                }
                builder.append(name).append(" - ").append(salary);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Can not parse", e);
        }
        return builder.insert(0,"Report for period " + dateFrom + " - " + dateTo).toString();
    }
}
