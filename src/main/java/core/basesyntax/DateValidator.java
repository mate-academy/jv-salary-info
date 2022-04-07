package core.basesyntax;

import java.util.Date;

public class DateValidator {
    private Date startDate;
    private Date endDate;

    public DateValidator(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isWithinRange(Date salaryDate) {
        boolean result = false;
        if ((salaryDate.equals(startDate) || salaryDate.equals(endDate))
                || (salaryDate.after(startDate) && salaryDate.before(endDate))) {
            result = true;
        }
        return result;
    }
}
