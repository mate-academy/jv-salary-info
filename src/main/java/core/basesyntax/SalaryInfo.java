package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String pattern = "dd.MM.yyyy";
    private static final DateTimeFormatter SIMPLE_DATE_FORMAT =
            DateTimeFormatter.ofPattern(pattern);
    private static final int DIFFERENCE_OF_DAYS = 1;
    private static final int DATE_NUM = 0;
    private static final int NAME_NUM = 1;
    private static final int NUMBER_OF_HOURS = 2;
    private static final int PRICE_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, SIMPLE_DATE_FORMAT)
                .minusDays(DIFFERENCE_OF_DAYS);
        LocalDate localDateTo = LocalDate.parse(dateTo, SIMPLE_DATE_FORMAT)
                .plusDays(DIFFERENCE_OF_DAYS);
        StringBuilder reportForPeriod = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String datum : data) {
                String[] record = datum.split(" ");
                LocalDate dateOfWork = LocalDate.parse(record[DATE_NUM], SIMPLE_DATE_FORMAT);
                if (name.equals(record[NAME_NUM])
                        && dateOfWork.isAfter(localDateFrom)
                        && dateOfWork.isBefore(localDateTo)) {
                    moneyEarned +=
                            Integer.parseInt(record[NUMBER_OF_HOURS])
                                    * Integer.parseInt(record[PRICE_PER_HOUR]);
                }
            }
            reportForPeriod.append(System.lineSeparator())
                    .append(name).append(" - ").append(moneyEarned);
        }
        return reportForPeriod.toString();
    }
}
