package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATA_DATE = 0;
    private static final int INDEX_DATA_NAME = 1;
    private static final int INDEX_DATA_TIME = 2;
    private static final int INDEX_DATA_MONEY = 3;
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] temporyData;
        int sum;
        StringBuilder outString = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            outString.append(System.lineSeparator());
            sum = 0;
            for (String datum : data) {
                temporyData = datum.split(" ");
                if (isDataOk(temporyData[INDEX_DATA_DATE], dateFrom, dateTo)) {
                    if (temporyData[INDEX_DATA_NAME].equals(name)) {
                        sum += Integer.parseInt(temporyData[INDEX_DATA_TIME])
                                * Integer.parseInt(temporyData[INDEX_DATA_MONEY]);
                    }
                }
            }
            outString.append(name).append(" - ").append(sum);
        }
        return outString.toString();
    }

    public boolean isDataOk(String date, String dateFrom, String dateTo) {
        return (((LocalDate.parse(date, PATTERN).isBefore(LocalDate.parse(dateTo, PATTERN)))
                || (LocalDate.parse(date, PATTERN).isEqual(LocalDate.parse(dateTo, PATTERN))))
                && ((LocalDate.parse(date, PATTERN).isAfter(LocalDate.parse(dateFrom, PATTERN)))
                || (LocalDate.parse(date, PATTERN).isEqual(LocalDate.parse(dateFrom, PATTERN)))));
    }
}
