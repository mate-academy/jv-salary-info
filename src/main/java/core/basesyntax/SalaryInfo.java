package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    //formatter of a date STATIC
    private static final int COLUMNS_IN_DATA = 4; //format of array String[] data
    private static final int COLUMN_WITH_DATE = 0;
    private static final int COLUMN_WITH_NAME = 1;
    private static final int COLUMN_WITH_HOURS = 2;
    private static final int COLUMN_WITH_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        int[] salaries = new int[names.length];
        //array with future salaries
        String[] splitData = new String[COLUMNS_IN_DATA];
        //array with splitted data from array data[]

        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        //first part of stringBuilder without information with salaries

        for (int i = 0; i < names.length; i++) { //going throw array of names
            for (String dayData : data) { // going throw data array
                splitData = dayData.split(" ");
                //split data line on 4 parts: 0date - 1name - 2hours - 3salaryHours
                if (splitData[COLUMN_WITH_NAME].equals(names[i])) {
                    if (checkDates(splitData[COLUMN_WITH_DATE], dateFrom, dateTo)) {
                        salaries[i] += Integer.valueOf(splitData[COLUMN_WITH_HOURS])
                                * Integer.valueOf(splitData[COLUMN_WITH_SALARY_PER_HOUR]);
                    }
                }
            }
            stringBuilder.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
            if (i != names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }

        return stringBuilder.toString();
    }

    private boolean checkDates(String currentDate, String dateFrom, String dateTo) {
        String[] checkFrom = dateFrom.split(" ");
        LocalDate dateFromFormatted = LocalDate.parse(checkFrom[0], FORMATTER);
        String[] checkTo = dateTo.split(" ");
        LocalDate dateToFormatted = LocalDate.parse(checkTo[0], FORMATTER);
        LocalDate dataFormatted = LocalDate.parse(currentDate, FORMATTER);

        if (dataFormatted.isEqual(dateFromFormatted)
                || dataFormatted.isEqual(dateToFormatted)
                || dataFormatted.isAfter(dateFromFormatted)
                && dataFormatted.isBefore(dateToFormatted)) {
            return true;
        }
        return false;
    }
}
