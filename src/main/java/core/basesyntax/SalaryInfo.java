package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ARRAY_INDEX_ZERO = 0;
    private static final int ARRAY_INDEX_ONE = 1;
    private static final int ARRAY_INDEX_TWO = 2;
    private static final int ARRAY_INDEX_TREE = 3;
    private static final String DELIMITER = " ";
    private static final String DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] salary = new int[names.length];
        String[] str;

        for (String datum : data) {
            str = datum.split(DELIMITER);
            String name = str[ARRAY_INDEX_ONE];
            LocalDate date = LocalDate.parse(str[ARRAY_INDEX_ZERO], FORMATTER);
            LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
            int salaryInDay = (Integer.parseInt(str[ARRAY_INDEX_TWO]) * Integer.parseInt(str[ARRAY_INDEX_TREE]));

            if ((date.isAfter(localDateFrom) || date.isEqual(localDateFrom))
                    && (date.isBefore(localDateTo) || date.isEqual(localDateTo))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(name)) {
                        salary[j] += salaryInDay;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(DASH)
                    .append(salary[i]);
        }
        return result.toString();
    }
}
