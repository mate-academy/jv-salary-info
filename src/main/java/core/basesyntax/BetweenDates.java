package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BetweenDates {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private Date dateFrom;
    private Date dateTo;

    public BetweenDates(String dateFrom, String dateTo) {
        try {
            this.dateFrom = formatter.parse(dateFrom);
            this.dateTo = formatter.parse(dateTo);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isBetweenDates(String date) {
        try {
            Date checkingDate = formatter.parse(date);
            return dateFrom.compareTo(checkingDate) <= 0 && dateTo.compareTo(checkingDate) >= 0;
        } catch (ParseException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
