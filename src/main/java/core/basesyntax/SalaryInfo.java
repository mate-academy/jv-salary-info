package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_TITLE = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final String Empty = " ";
    private static final int ZERO_POSITION = 0;
    private static final int FIRST_POSITION = 1;
    private static final int SECOND_POSITION = 2;
    private static final int THIRD_POSITION = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder(REPORT_TITLE)
                .append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int income = 0;
            for (String dataString : data) {
                String[] splitData = dataString.split(Empty);
                int hoursInData = Integer.parseInt(splitData[SECOND_POSITION]);
                int incomePerHourInData = Integer.parseInt(splitData[THIRD_POSITION]);
                LocalDate date = LocalDate.parse(splitData[ZERO_POSITION], FORMATTER);
                String nameInData = splitData[FIRST_POSITION];
                if (nameInData.equals(name) && fromDate.isBefore(date)
                        && (toDate.isEqual(date) || toDate.isAfter(date))) {
                    income = income + (hoursInData * incomePerHourInData);
                }
            }
            result.append(System.lineSeparator()).append(name).append(SEPARATOR).append(income);
        }
        return result.toString();
    }
}
