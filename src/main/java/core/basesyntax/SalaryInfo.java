package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int AMOUNT_INDEX = 3;
    private static final String DIVIDER = " - ";
    private static final String START_REPORT = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder(
                START_REPORT + dateFrom + DIVIDER + dateTo);
        String[] semiResult;
        LocalDate dataDate;
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            for (String line : data) {
                semiResult = line.split(" ");
                dataDate = LocalDate.parse(semiResult[DATE_INDEX], FORMATTER);
                if (isFulfillConditions(semiResult, dataDate, fromDate, toDate, names[i])) {
                    sum = sum + Integer.parseInt(semiResult[HOURS_INDEX])
                            * Integer.parseInt(semiResult[AMOUNT_INDEX]);
                }
            }
            stringBuilder
                    .append(System.lineSeparator())
                    .append(names[i])
                    .append(DIVIDER)
                    .append(sum);
            sum = 0;
            if (i == names.length - 1) {
                break;
            }
        }
        return stringBuilder.toString();
    }

    private boolean isFulfillConditions(String[] semiResult, LocalDate dataDate,
                                        LocalDate fromDate, LocalDate toDate, String names) {
        return names.equals(semiResult[1])
                && ((dataDate.isEqual(fromDate)
                || dataDate.isEqual(toDate))
                || dataDate.isAfter(fromDate)
                && dataDate.isBefore(toDate));
    }
}
