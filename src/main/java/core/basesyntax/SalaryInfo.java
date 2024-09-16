package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DEFAULT_DATA_TO_ARRAY = 0;
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int COUNT_POSITION = 2;
    private static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String header = "Report for period " + dateFrom + " - " + dateTo;
        StringBuilder stringBuilder = new StringBuilder(header);

        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        int[] salaries = new int[names.length];
        Arrays.fill(salaries, DEFAULT_DATA_TO_ARRAY);

        for (int i = 0; i < names.length; i++) {
            for (String dataItem : data) {
                String[] splitData = dataItem.split(" ");
                LocalDate localDateFromData = LocalDate
                        .parse(splitData[DATE_POSITION],DATE_TIME_FORMATTER);

                if (names[i].equals(splitData[NAME_POSITION])
                        && !localDateFromData.isBefore(localDateFrom)
                        && !localDateFromData.isAfter(localDateTo)) {
                    salaries[i] += Integer.parseInt(splitData[COUNT_POSITION])
                            * Integer.parseInt(splitData[SALARY_POSITION]);
                }
            }

            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaries[i]);
        }

        return stringBuilder.toString();
    }
}
