package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConvertor {
    public LocalDate convert(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate beginningOfThePeriod = LocalDate.parse(strDate, dateTimeFormatter);
        return beginningOfThePeriod;
    }

}
