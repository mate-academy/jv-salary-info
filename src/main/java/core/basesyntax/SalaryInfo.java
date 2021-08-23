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
        int[] sumSallary = new int[names.length];
        String[] temporyData;
        for (String datum : data) {
            temporyData = datum.split(" ");
            if (isDataOk(temporyData[INDEX_DATA_DATE], dateFrom, dateTo)) {
                for (int i = 0; i < names.length; i++) {
                    if (temporyData[INDEX_DATA_NAME].equals(names[i])) {
                        sumSallary[i] += Integer.parseInt(temporyData[INDEX_DATA_TIME])
                                * Integer.parseInt(temporyData[INDEX_DATA_MONEY]);
                    }
                }
            }
        }
        return result(sumSallary, names, dateFrom, dateTo);
    }

    public String result(int[] sumSallary, String[] names, String dateFrom, String dateTo) {
        StringBuilder outString = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i != names.length - 1) {
                outString.append(names[i]).append(" - ")
                        .append(sumSallary[i])
                        .append(System.lineSeparator());
            } else {
                outString.append(names[i]).append(" - ")
                        .append(sumSallary[i]);
            }
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
