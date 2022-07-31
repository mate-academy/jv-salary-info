package core.basesyntax.services;

import java.util.Date;

public class CompareDate {
    private final Date fromDate;
    private final Date toDate;

    public CompareDate(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public boolean isBetween(Date currentDate) {
        return !currentDate.before(fromDate) && !currentDate.after(toDate);
    }
}
