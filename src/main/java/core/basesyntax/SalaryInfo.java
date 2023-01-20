package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ONE_DAY = 1;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateFromNew = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate fromNew = dateFromNew.minusDays(ONE_DAY);
        LocalDate dateToNew = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate toNew = dateToNew.plusDays(ONE_DAY);
        String[] dataArray;
        LocalDate dateX;
        int balance = 0;

        for (String name : names) {
            balance = 0;
            for (String line : data) {
                dataArray = line.split(" ");
                if (dataArray[NAME_INDEX ].equals(name)) {
                    dateX = LocalDate.parse(dataArray[DATE_INDEX], DATE_TIME_FORMATTER);
                    if (dateX.isAfter(fromNew) && dateX.isBefore(toNew)) {
                        balance += Integer.parseInt(dataArray[HOURS_INDEX])
                                * Integer.parseInt(dataArray[SALARY_INDEX]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(balance);
        }
        return builder.toString();
    }
}
