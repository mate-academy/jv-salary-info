package core.basesyntax;

import static java.lang.Integer.parseInt;

public class GetDateInNumbers {
    public final int getDateInNumbers(String dateOnlyString) {
        String[] arrayDateFrom = dateOnlyString.split("\\.");
        int numberValues = 0;
        int counter = 0;
        for (int i = 0; i < arrayDateFrom.length; i++) {
            if (counter == 1) {
                numberValues += 10 * parseInt(dateOnlyString.split("\\.")[i]);
            } else {
                numberValues += parseInt(dateOnlyString.split("\\.")[i]);
            }
            counter++;
        }
        return numberValues;
    }
}
