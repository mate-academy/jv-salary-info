package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalaryInfo {
    private static final int INDEX_OF_DATES = 0;
    private static final int INDEX_OF_NAMES = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_WAGES = 3;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        return null;
    }

    public ArrayList<Integer> getIndexesOfSpecifiedDates (LocalDate[]datesBase, LocalDate parsedDateFrom, LocalDate parsedDateTo){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < datesBase.length; i++) {
            if (!parsedDateFrom.isAfter(datesBase[i]) && !parsedDateTo.isBefore(datesBase[i])) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public LocalDate[] createDatesArray(String[]data) {
        LocalDate[] dates = new LocalDate[data.length];
        for (int i = 0; i < data.length; i++) {
            dates[i] = LocalDate.parse(data[i].split(" ")[INDEX_OF_DATES], TIME_FORMATTER);
        }
        return dates;
    }

    public String[] createNamesArray(String[]data) {
        String[] names = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            names[i] = data[i].split(" ")[INDEX_OF_NAMES];
        }
        return names;
    }

    public int[] createHoursArray (String[]data) {
        int[] hours = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            hours[i] = Integer.parseInt(data[i].split(" ")[INDEX_OF_HOURS]);
        }
        return hours;
    }
    public int[] createWagesArray (String[]data) {
        int[] wages = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            wages[i] = Integer.parseInt(data[i].split(" ")[INDEX_OF_WAGES]);
        }
        return wages;
    }
}
