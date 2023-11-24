package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String HEADER = "Report for period ";
    private static final String DELIMITER = " ";
    private static final String SEPARATOR = " - ";
    private static final int ARRAY_INDEX_ZERO = 0;
    private static final int ARRAY_INDEX_ONE = 1;
    private static final int ARRAY_INDEX_TWO = 2;
    private static final int ARRAY_INDEX_THREE = 3;
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder(HEADER + dateFrom + SEPARATOR + dateTo);
        int[]totalSalary = new int[names.length];

        for (int n = 0; n < names.length; n++) {
            for (String datum : data) {
                String [] dataArray = datum.split(DELIMITER);
                LocalDate workingDate = LocalDate.parse(dataArray[ARRAY_INDEX_ZERO],DATE_FORMATTER);
                if (names[n].equals(dataArray[ARRAY_INDEX_ONE])
                        && workingDate.isBefore(LocalDate.parse(dateTo,DATE_FORMATTER).plusDays(1))
                        && workingDate.isAfter(LocalDate.parse(dateFrom,DATE_FORMATTER))) {
                    totalSalary[n] += Integer.parseInt(dataArray[ARRAY_INDEX_TWO])
                             * Integer.parseInt(dataArray[ARRAY_INDEX_THREE]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[n])
                    .append(SEPARATOR).append(totalSalary[n]);
        }
        return stringBuilder.toString();
    }
}
