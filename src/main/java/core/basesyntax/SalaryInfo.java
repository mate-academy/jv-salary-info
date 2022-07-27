package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int COLUMNS_IN_DATA = 4;
    private static final int COLUMN_WITH_DATE = 0;
    private static final int COLUMN_WITH_NAME = 1;
    private static final int COLUMN_WITH_HOURS = 2;
    private static final int COLUMN_WITH_SALARY_PER_HOUR = 3;
    private static final String DATA_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        String[] splitData = new String[COLUMNS_IN_DATA];
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            for (String dayData : data) {
                splitData = dayData.split(DATA_SEPARATOR);
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
        String[] checkFrom = dateFrom.split(DATA_SEPARATOR);
        LocalDate dateFromFormatted = LocalDate.parse(checkFrom[0], FORMATTER);
        String[] checkTo = dateTo.split(DATA_SEPARATOR);
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
