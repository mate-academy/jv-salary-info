package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int AMOUNT_INDEX = 3;
    private static final String DIVIDER = " - ";
    private static final String START_REPORT = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder(
                START_REPORT + dateFrom + DIVIDER + dateTo);
        String[] row;
        LocalDate dataDate;
        int sum = 0;
        for (int i = 0; i < names.length; i++) {
            for (String line : data) {
                row = line.split(" ");
                dataDate = LocalDate.parse(row[DATE_INDEX], FORMATTER);
                if (isFulfillConditions(row, dataDate, fromDate, toDate, names[i])) {
                    sum += Integer.parseInt(row[HOURS_INDEX])
                            * Integer.parseInt(row[AMOUNT_INDEX]);
                }
            }
            result
                    .append(System.lineSeparator())
                    .append(names[i])
                    .append(DIVIDER)
                    .append(sum);
            sum = 0;
        }
        return result.toString();
    }

    private boolean isFulfillConditions(String[] semiResult, LocalDate dataDate,
                                        LocalDate fromDate, LocalDate toDate, String names) {
        return names.equals(semiResult[NAME_INDEX])
                && ((dataDate.isEqual(fromDate)
                || dataDate.isEqual(toDate))
                || dataDate.isAfter(fromDate)
                && dataDate.isBefore(toDate));
    }
}
