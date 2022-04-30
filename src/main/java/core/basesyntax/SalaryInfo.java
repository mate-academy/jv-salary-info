package core.basesyntax;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final DateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        try {
            Timestamp fromTime = dateParse(dateFrom);
            Timestamp toTime = dateParse(dateTo);
            for (String name : names) {
                int sum = 0;
                for (String info : data) {
                    String[] dateArray = info.split(" ");
                    if (checkDate(dateParse(dateArray[InfoType.DATE.ordinal()]), fromTime, toTime)
                            && info.contains(name)) {
                        sum += Integer.parseInt(dateArray[InfoType.WORKING_HOUR.ordinal()])
                                * Integer.parseInt(dateArray[InfoType.INCOME_PER_HOUR.ordinal()]);
                    }
                }
                result.append(System.lineSeparator())
                        .append(name)
                        .append(" - ")
                        .append(sum);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }

    public static Timestamp dateParse(String dateToParse) throws ParseException {
        Date date = FORMATTER.parse(dateToParse);
        return new Timestamp(date.getTime());
    }

    public static boolean checkDate(Timestamp dateToCheck, Timestamp fromTime, Timestamp toTime) {
        return dateToCheck.after(fromTime) && dateToCheck.before(toTime)
                || dateToCheck.equals(fromTime) || dateToCheck.equals(toTime);
    }

}
