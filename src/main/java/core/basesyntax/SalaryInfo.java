package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int HOURS_OF_WORK = 2;
    private static final int INDEX_INCOME_PER_HOUR = 3;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int totalSalaryForPerson;
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            totalSalaryForPerson = 0;
            try {
                for (String currData : data) {
                    String[] fullInfo = currData.split(" ");
                    Date checkDate = dateFormat.parse(fullInfo[0]);
                    Date checkFromDate = dateFormat.parse(dateFrom);
                    Date checkToDate = dateFormat.parse(dateTo);
                    if (checkFromDate.compareTo(checkDate) <= 0
                            && checkToDate.compareTo(checkDate) >= 0) {
                        if (fullInfo[1].equals(name)) {
                            int hoursOfWork = Integer.parseInt(fullInfo[HOURS_OF_WORK]);
                            int incomePerHour = Integer.parseInt(fullInfo[INDEX_INCOME_PER_HOUR]);
                            totalSalaryForPerson += hoursOfWork * incomePerHour;
                        }
                    }
                }
            } catch (ParseException e) {
                System.out.println("ParseException was detected, try with another parameters");
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalaryForPerson);
        }
        return builder.toString();
    }
}
