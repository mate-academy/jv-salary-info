package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String RETURN_ROW = "Report for period ";
    private static final String HYPHEN = " - ";
    private static final String SEPARATOR = " ";
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder(RETURN_ROW + dateFrom + HYPHEN + dateTo);
        String[] info;
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int sum = 0;
            for (String datum : data) {
                info = datum.split(SEPARATOR);
                LocalDate actualDate = LocalDate.parse(info[INDEX_OF_DATE], formatter);
                if ((actualDate.isAfter(from.minusDays(1)))
                        && (actualDate.isBefore(endDate.plusDays(1)))) {
                    if (name.equals(info[INDEX_OF_NAME])) {
                        sum = sum + Integer.parseInt(info[INDEX_OF_HOURS])
                                * Integer.parseInt(info[INDEX_OF_SALARY]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(HYPHEN).append(sum);
        }
        return builder.toString();
    }
}
