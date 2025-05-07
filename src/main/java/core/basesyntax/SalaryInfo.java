package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int AMOUNT_INDEX_1 = 2;
    private static final int AMOUNT_INDEX_2 = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDate;
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] sum = new int[names.length];
        for (String string: data) {
            String[] split = string.split(" ");
            localDate = LocalDate.parse(split[DATE_INDEX], FORMATTER);
            if (!localDate.isAfter(LocalDate.parse(dateTo, FORMATTER))
                    && !localDate.isBefore(LocalDate.parse(dateFrom, FORMATTER))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(split[NAME_INDEX])) {
                        sum[i] += Integer.parseInt(split[AMOUNT_INDEX_1])
                                * Integer.parseInt(split[AMOUNT_INDEX_2]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(sum[i]);
        }
        return result.toString();
    }
}
