package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {
    public boolean isWorked(String strDateFrom, String strDateTo, String workDay)
            throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");

        Date dateFromWork = formatDate.parse(strDateFrom);
        Date dateToWork = formatDate.parse(strDateTo);
        Date jobDay = formatDate.parse(workDay);
        return (jobDay.compareTo(dateFromWork) >= 0 && jobDay.compareTo(dateToWork) <= 0);
    }
}
