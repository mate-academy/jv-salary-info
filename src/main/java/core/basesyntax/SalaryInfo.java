package core.basesyntax;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder string = new StringBuilder();
        string.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int money = 0;
            for (String eachData : data) {
                String[] eachDataArray = eachData.split(" ");
                if (name.equals(eachDataArray[1])
                        && (getDate(eachDataArray[0]).equals(getDate(dateFrom))
                        || getDate(eachDataArray[0]).after(getDate(dateFrom)))
                        && (getDate(eachDataArray[0]).equals(getDate(dateTo))
                        || getDate(eachDataArray[0]).before(getDate(dateTo)))) {
                    money += Integer.parseInt(eachDataArray[2])
                            * Integer.parseInt(eachDataArray[3]);
                }
            }
            string.append(System.lineSeparator()).append(name).append(" - ").append(money);
        }
        return string.toString();
    }

    private static Calendar getDate(String string) {
        String[] date = string.split("\\.");
        return new GregorianCalendar(Integer.parseInt(date[1]),
                Integer.parseInt(date[1]), Integer.parseInt(date[0]));
    }
}
