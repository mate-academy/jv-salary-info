package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static Date parseDate(String date) {
        Date parsedDate = null;
        try {
            parsedDate = new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            System.out.println("Wrong date format");
        }
        return parsedDate;
    }
}
