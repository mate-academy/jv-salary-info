package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
        private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        private static final int INDEX_DATA_IN_ARRAY = 0;
        private static final int INDEX_NAME_IN_ARRAY = 1;
        private static final int INDEX_WORKING_HOUR_IN_ARRAY = 2;
        private static final int INDEX_PER_HOUR_IN_ARRAY = 3;


        public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String[] splitDataToArray;
        int[] salaryInfo = new int[names.length];
        final LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        final LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (int j = 0; j < names.length; j++) {
            for (String datum : data) {
                splitDataToArray = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(splitDataToArray[INDEX_DATA_IN_ARRAY], DATE_TIME_FORMATTER);
                if (names[j].equals(splitDataToArray[INDEX_NAME_IN_ARRAY])
                        && (currentDate.isAfter(fromDate) && currentDate.isBefore(toDate)
                        || currentDate.equals(fromDate)
                        || currentDate.equals(toDate))) {
                    salaryInfo[j] = salaryInfo[j] + (Integer.parseInt(splitDataToArray[INDEX_WORKING_HOUR_IN_ARRAY])
                            * Integer.parseInt(splitDataToArray[INDEX_PER_HOUR_IN_ARRAY]));
                }
            }
        }
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaryInfo[i]);
        }
        return builder.toString();
    }
}
