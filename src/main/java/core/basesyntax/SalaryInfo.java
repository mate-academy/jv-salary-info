package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int NUMBERS_INDEX = 2;
    private static final int VALUE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] sum = new int[names.length];
        for (String string: data) {
            String[] split = string.split(" ");
            localDate = LocalDate.parse(split[DATE_INDEX],formatter);
            if (!localDate.isAfter(LocalDate.parse(dateTo,formatter))
                    && !localDate.isBefore(LocalDate.parse(dateFrom,formatter))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(split[NAME_INDEX])) {
                        sum[i] += Integer.parseInt(split[NUMBERS_INDEX])
                                * Integer.parseInt(split[VALUE_INDEX]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator() + names[i] + " - " + sum[i]);
        }
        return result.toString();
    }
}
