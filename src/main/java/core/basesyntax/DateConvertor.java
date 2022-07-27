package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConvertor {
    public LocalDate convert(String strData) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate beginningOfThePeriod = LocalDate.parse(strData, dateTimeFormatter);
        return beginningOfThePeriod;
    }

}
