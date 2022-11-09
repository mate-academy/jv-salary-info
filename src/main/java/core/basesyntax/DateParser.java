package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    LocalDate dateParsing(String date){
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate resultDate;
        try {
            resultDate = LocalDate.parse(date, formatter);
        }
        catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", date);
            throw exc;      // Rethrow the exception.
        }
        return resultDate;
    }
}
