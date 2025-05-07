package core.basesyntax;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MPH_INDEX = 3;
    private static final String DOT_MILLIMETRE = "\\.";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder string = new StringBuilder();
        Calendar dateFirst = getDate(dateFrom);
        Calendar dateLast = getDate(dateTo);
        string.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int money = 0;
            for (String eachData : data) {
                String[] eachDataArray = eachData.split(" ");
                Calendar dateNeed = getDate(eachDataArray[DATE_INDEX]);
                if (name.equals(eachDataArray[NAME_INDEX])
                        && (dateNeed.equals(dateFirst) || dateNeed.after(dateFirst))
                        && (dateNeed.equals(dateLast) || dateNeed.before(dateLast))) {
                    money += Integer.parseInt(eachDataArray[HOURS_INDEX])
                            * Integer.parseInt(eachDataArray[MPH_INDEX]);
                }
            }
            string.append(System.lineSeparator()).append(name).append(" - ").append(money);
        }
        return string.toString();
    }

    private Calendar getDate(String string) {
        String[] date = string.split(DOT_MILLIMETRE);
        return new GregorianCalendar(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]), Integer.parseInt(date[0]));
    }
}
