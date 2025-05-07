package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int PAYMENT = 3;

    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportForPeriod = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSumForPeriod = 0;
            for (String workDate : data) {
                if (workDate.contains(name)) {
                    String[] splittedDataArray = workDate.split(" ");
                    LocalDate workDay = LocalDate.parse(splittedDataArray[DATE], FORMATTER);
                    if (workDay.isAfter(from) && workDay.isBefore(to)
                            || workDay.isEqual(from) || workDay.isEqual(to)) {
                        totalSumForPeriod = Integer.parseInt(splittedDataArray[WORK_HOURS])
                                * Integer.parseInt(splittedDataArray[PAYMENT])
                                + totalSumForPeriod;
                    }
                }
            }
            reportForPeriod
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSumForPeriod);
        }
        return reportForPeriod.toString();
    }
}
