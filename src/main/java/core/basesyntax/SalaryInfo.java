package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalaryInfo {
    private static final int INDEX_OF_DATES = 0;
    private static final int INDEX_OF_NAMES = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_WAGES = 3;
    private static final DateTimeFormatter TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate[] datesArray = createDatesArray(data);
        String[] namesArray = createNamesArray(data);
        int[] hoursArray = createHoursArray(data);
        int[] wagesArray = createWagesArray(data);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate parsedDateTo
                = LocalDate.parse(dateTo, TIME_FORMATTER);
        ArrayList<Integer> indexes
                = getIndexesOfSpecifiedDates(datesArray, parsedDateFrom, parsedDateTo);
        int[] salaries = calculateSalary(indexes, names, namesArray, hoursArray, wagesArray);
        return createReport(names, salaries, dateFrom, dateTo);
    }

    public String createReport(String[] names, int[] salaries, String dateFrom, String dateTo) {
        String string = "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator();
        StringBuilder builder = new StringBuilder(string);
        for (int i = 0; i < names.length; i++) {
            if (i != names.length - 1) {
                builder.append(names[i]).append(" - ")
                        .append(salaries[i])
                        .append(System.lineSeparator());
            } else {
                builder.append(names[i]).append(" - ").append(salaries[i]);
            }
        }
        return builder.toString();
    }

    public int[] calculateSalary(ArrayList<Integer> indexes, String[] names,
                                 String[] namesArray, int[] hoursArray, int[] wagesArray) {
        int[] salaries = new int[names.length];
        if (indexes == null) {
            return new int[] {0, 0, 0};
        }
        for (Integer integer : indexes) {
            for (int j = 0; j < names.length; j++) {
                int index = integer;
                if (names[j].equals(namesArray[index])) {
                    salaries[j] += hoursArray[index] * wagesArray[index];
                }
            }
        }
        return salaries;
    }

    public ArrayList<Integer> getIndexesOfSpecifiedDates(LocalDate[]datesBase,
                                                         LocalDate parsedDateFrom,
                                                         LocalDate parsedDateTo) {
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

    public int[] createHoursArray(String[]data) {
        int[] hours = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            hours[i] = Integer.parseInt(data[i].split(" ")[INDEX_OF_HOURS]);
        }
        return hours;
    }

    public int[] createWagesArray(String[]data) {
        int[] wages = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            wages[i] = Integer.parseInt(data[i].split(" ")[INDEX_OF_WAGES]);
        }
        return wages;
    }
}
