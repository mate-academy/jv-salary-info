package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CompareTwoDates {
    public static final String FORMATTER = "dd.MM.yyyy";

    public boolean comparator(String dateFrom, String dateBetween, String dateTo) {
        SimpleDateFormat format = new SimpleDateFormat(FORMATTER);
        try {
            return format.parse(dateFrom).compareTo(format.parse(dateBetween)) <= 0
                    && format.parse(dateBetween).compareTo(format.parse(dateTo)) <= 0;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
