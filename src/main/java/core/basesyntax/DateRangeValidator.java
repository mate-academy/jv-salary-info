package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeValidator {
    private final String startDate;
    private final String endDate;

    public DateRangeValidator(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isWithinRange(String particularDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate particularDate = LocalDate.parse(particularDay, formatter);
        LocalDate startDay = LocalDate.parse(startDate, formatter);
        LocalDate endDay = LocalDate.parse(endDate, formatter);
        return particularDate.isAfter(startDay) && particularDate.isBefore(endDay)
                || particularDate.isEqual(startDay) || particularDate.isEqual(endDay);
    }
}
