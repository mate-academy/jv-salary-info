package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConvertor {
    public static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate convert(String strDate) {
        return LocalDate.parse(strDate, DT_FORMATTER);
    }
}
