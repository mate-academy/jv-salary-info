package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataComparison {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public boolean isDateInRange(String fromDate, String toDate, String dateFromAList) {

        try {
            LocalDate startDate = LocalDate.parse(fromDate, FORMATTER);
            LocalDate endDate = LocalDate.parse(toDate, FORMATTER);
            LocalDate dateToCheck = LocalDate.parse(dateFromAList, FORMATTER);

            return ((dateToCheck.isEqual(startDate) || dateToCheck.isAfter(startDate))
                    && (dateToCheck.isEqual(endDate) || dateToCheck.isBefore(endDate)));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format" + e);
            return false;
        }
    }

}
