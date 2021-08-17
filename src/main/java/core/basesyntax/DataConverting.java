package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverting {
    public Date dataConverter(String date) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
        return  formatter1.parse(date);
    }
}
