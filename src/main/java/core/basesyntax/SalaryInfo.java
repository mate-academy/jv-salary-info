package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String DASH = " - ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String REPORT_FOR_PERIOD = "Report for period ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate localDateTo = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern(DATE_FORMAT));
        StringBuilder result = new StringBuilder(REPORT_FOR_PERIOD)
                .append(dateFrom)
                .append(DASH)
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int sum = 0;
            for (String test : data) {
                if (test == null) {
                    continue;
                }
                String[] parts = test.split(SPACE);
                if (parts[NAME_INDEX].equals(name)) {
                    String date = parts[DATE_INDEX];
                    LocalDate localDate = LocalDate.parse(date,
                            DateTimeFormatter.ofPattern(DATE_FORMAT));
                    if (localDate.compareTo(localDateFrom) >= 0
                            && localDate.compareTo(localDateTo) <= 0) {
                        int hours = Integer.parseInt(parts[HOURS_INDEX]);
                        int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
                        sum += hours * incomePerHour;
                    }
                }
            }
            result.append(name)
                    .append(DASH)
                    .append(sum)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
