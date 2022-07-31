package core.basesyntax.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseService {
    public Date stringToDate(String inputDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(inputDate);
        } catch (ParseException e) {
            throw new RuntimeException("Can not parse string to java.util.date", e);
        }
    }
}
