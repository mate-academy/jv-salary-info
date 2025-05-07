package core.basesyntax;

import java.time.LocalDate;

public class DateService {
    public LocalDate convertToDate(String stringDate) {
        String[] dateArray = stringDate.split("\\.");
        try {
            return LocalDate.of(Integer.parseInt(dateArray[2]),
                    Integer.parseInt(dateArray[1]),
                    Integer.parseInt(dateArray[0]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not in date format DD.MM.YYYY " + stringDate, e);
        }
    }

    public boolean isBetween(String currentDate, String fromDate, String toDate) {
        return convertToDate(currentDate).equals(convertToDate(fromDate))
                || convertToDate(currentDate).equals(convertToDate(toDate))
                || (convertToDate(currentDate).isAfter(convertToDate(fromDate))
                && convertToDate(currentDate).isBefore(convertToDate(toDate)));
    }
}
