package core.basesyntax;

import java.time.LocalDate;

public class DateSupplier {
    public LocalDate convertToDate(String strDate) {
        String[] strArray = strDate.split("\\.");
        try {
            return LocalDate.of(Integer.parseInt(strArray[2]),
                    Integer.parseInt(strArray[1]),
                    Integer.parseInt(strArray[0]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not in date format DD.MM.YYYY", e);
        }
    }

    public boolean isBetween(String currentDate, String fromDate, String toDate) {
        return convertToDate(currentDate).equals(convertToDate(fromDate))
                || convertToDate(currentDate).equals(convertToDate(toDate))
                || (convertToDate(currentDate).isAfter(convertToDate(fromDate))
                && convertToDate(currentDate).isBefore(convertToDate(toDate)));
    }
}
