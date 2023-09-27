package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final int NUMBER_OF_INDEXES = 4;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate dateFromFormatted = getDate(dateFrom);
        LocalDate dateToFormatted = getDate(dateTo);
        LocalDate dateInOneRowFromData;
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int income = 0;
            for (int j = 0; j < data.length; j++) {
                dateInOneRowFromData = getDate(splitData(data[j], DATE_INDEX));
                if (names[i].equals(splitData(data[j], NAME_INDEX))
                        && (dateFromFormatted.isBefore(dateInOneRowFromData)
                        || dateFromFormatted.equals(dateInOneRowFromData))
                        && (dateToFormatted.isAfter(dateInOneRowFromData)
                        || dateToFormatted.equals(dateInOneRowFromData))) {
                    // we multiply the hours with the salary per hour
                    income += Integer.parseInt(splitData(data[j], HOURS_INDEX))
                            * Integer.parseInt(splitData(data[j], SALARY_INDEX));
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(income);
        }
        return builder.toString();
    }

    /* returns the value in String from a single row of the date array,
     when i=0 it returns the date, when i=1 it returns the name,
     when i=2 it returns working hours, when i=3 it returns the salary */
    private String splitData(String singularRowData, int i) {
        String[] arrayOfRow = singularRowData.split(" ", NUMBER_OF_INDEXES);
        return arrayOfRow[i];
    }

    // returns LocalDate format from String
    private LocalDate getDate(String stringDate) {
        return LocalDate.parse(stringDate, formatter);
    }
}
