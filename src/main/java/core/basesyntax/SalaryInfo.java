package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATA = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_PER_HOUR = 3;
    private static final String SEPARATOR = " - ";

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String[] splitDataToArray;
        int[] salaryInfo = new int[names.length];
        final LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        final LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (int j = 0; j < names.length; j++) {
            for (String datum : data) {
                splitDataToArray = datum.split(" ");
                LocalDate currentDate
                        = LocalDate.parse(splitDataToArray[INDEX_DATA], DATE_FORMATTER);
                if (names[j].equals(splitDataToArray[INDEX_NAME])
                        && (currentDate.isAfter(fromDate) && currentDate.isBefore(toDate)
                        || currentDate.equals(fromDate)
                        || currentDate.equals(toDate))) {
                    salaryInfo[j] = salaryInfo[j] + (Integer.parseInt(splitDataToArray[INDEX_HOUR])
                            * Integer.parseInt(splitDataToArray[INDEX_PER_HOUR]));
                }
            }
        }
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom
                + SEPARATOR
                + dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaryInfo[i]);
        }
        return builder.toString();
    }
}
