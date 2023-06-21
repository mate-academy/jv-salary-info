package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        int[] sum = new int[names.length];
        LocalDate beginningOfPeriod = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endOfPeriod = LocalDate.parse(dateTo,FORMATTER);
        for (int i = 0; i < data.length; i++) {
            String[] dataArray = data[i].split(" ");
            LocalDate workingDay = LocalDate.parse(dataArray[INDEX_ZERO], FORMATTER);
            if (!workingDay.isBefore(beginningOfPeriod) && !workingDay.isAfter(endOfPeriod)) {
                for (int k = 0; k < names.length; k++) {
                    if (names[k].equals(dataArray[INDEX_ONE])) {
                        sum[k] += Integer.parseInt(dataArray[INDEX_TWO])
                                * Integer.parseInt(dataArray[INDEX_THREE]);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            result.append(names[i] + " - " + sum[i] + System.lineSeparator());
        }
        return result.toString().trim();
    }
}
