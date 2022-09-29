package core.basesyntax;

import java.time.LocalDate;

public class DataChecker {
    boolean isWithinRange(LocalDate firstDate, LocalDate targetDate, LocalDate dateTo) {
        return targetDate.isAfter(firstDate.minusDays(1))
                && targetDate.isBefore(dateTo.plusDays(1));
    }
}
