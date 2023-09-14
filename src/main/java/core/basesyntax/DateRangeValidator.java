package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeValidator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRangeValidator(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate, FORMATTER);
        this.endDate = LocalDate.parse(endDate, FORMATTER);
    }

    public boolean isWithinRange(String particularDay) {
        LocalDate particularDate = LocalDate.parse(particularDay, FORMATTER);
        return particularDate.isAfter(startDate) && particularDate.isBefore(endDate)
                || particularDate.isEqual(startDate) || particularDate.isEqual(endDate);
    }
}
