package core.basesyntax.suppliers;

public class DateSupplier {
    private static final int DAY_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static final int WORKING_DATE_INDEX = 0;

    private int[] parseDate(String data) {
        int[] intParsedDate = new int[3]; // {day, month, year}
        String[] stringParsedDate = data.split(" ")[WORKING_DATE_INDEX].split("\\.");
        for (int i = 0; i < intParsedDate.length; i++) {
            intParsedDate[i] = Integer.parseInt(stringParsedDate[i]);
        }
        return intParsedDate;
    }

    public boolean isDateInInterval(String data, String dateFrom, String dateTo) {
        int[] parsedDate = parseDate(data);
        int[] parsedDateFrom = parseDate(dateFrom);
        int[] parsedDateTo = parseDate(dateTo);
        if (parsedDate[YEAR_INDEX] < parsedDateTo[YEAR_INDEX]
                && parsedDate[YEAR_INDEX] > parsedDateFrom[YEAR_INDEX]) {
            return true;
        }
        if (parsedDate[YEAR_INDEX] == parsedDateTo[YEAR_INDEX]
                || parsedDate[YEAR_INDEX] == parsedDateFrom[YEAR_INDEX]) {
            if (parsedDate[MONTH_INDEX] < parsedDateTo[MONTH_INDEX]
                    && parsedDate[MONTH_INDEX] > parsedDateFrom[MONTH_INDEX]) {
                return true;
            }
            if (parsedDate[MONTH_INDEX] == parsedDateTo[MONTH_INDEX]) {
                return parsedDate[DAY_INDEX] <= parsedDateTo[DAY_INDEX];
            }
            if (parsedDate[MONTH_INDEX] == parsedDateFrom[MONTH_INDEX]) {
                return parsedDate[DAY_INDEX] >= parsedDateFrom[DAY_INDEX];
            }
        }
        return false;
    }
}
