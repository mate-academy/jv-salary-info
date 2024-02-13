package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    static final int DATE = 0;
    static final int NAME = 1;
    static final int WORKING_HOUR = 2;
    static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] perHour = new int[names.length];
        String[] splitedString = new String[4];
        StringBuilder stringBuilder = new StringBuilder("Report for period ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);
        LocalDate parseSplitedDate = LocalDate.parse(splitedString[DATE], formatter);

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                splitedString = data[j].split(" ");
                if ((parseSplitedDate.isAfter(parseDateFrom)
                        || parseSplitedDate.isEqual(parseDateFrom))
                        && (parseSplitedDate.isBefore(parseDateTo)
                        || parseSplitedDate.isEqual(parseDateTo))) {
                    if (splitedString[NAME].equals(names[i])) {
                        perHour[i] += (Integer.parseInt(splitedString[WORKING_HOUR])
                                * Integer.parseInt(splitedString[INCOME_PER_HOUR]));
                    }
                }
            }
            stringBuilder.append("\n" + names[i] + " - " + perHour[i]);
        }
        return stringBuilder.toString();
    }
}
