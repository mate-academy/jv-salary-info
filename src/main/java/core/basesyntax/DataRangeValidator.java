package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataRangeValidator {
    private LocalDate fromDate;
    private LocalDate toDate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void dateRangeValidator(String fromDate, String toDate) {
        this.fromDate = LocalDate.parse(fromDate, formatter);
        this.toDate = LocalDate.parse(toDate, formatter);
    }

    public boolean isWithinRange(String employeeJobDate) {
        LocalDate dates = LocalDate.parse(employeeJobDate, formatter);

        if ((dates.isEqual(fromDate) || dates.isEqual(toDate))
                || (dates.isAfter(fromDate) && dates.isBefore(toDate))) {
            return true;
        }
        return false;
    }
}
