package core.basesyntax;

import java.util.Date;

public class SalaryInfo {
    StringBuilder result = new StringBuilder();
    private static final int ONE_DAY = 3_600_000;
    private static final int HOURS_PER_DAY = 24;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        result.append("Result for period" ).append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        String[] dateFromSeparateArray = dateFrom.split("\\.");
        String[] dateToSeparateArray = dateTo.split("\\.");
        int[] dateFromNumber = new int[dateFromSeparateArray.length];
        int[] dateToNumber = new int[dateToSeparateArray.length];
        for (int i = 0; i < 3; i++) {
            dateFromNumber[i] =  Integer.parseInt(dateFromSeparateArray[i]);
            dateToNumber[i] =  Integer.parseInt(dateToSeparateArray[i]);
        }
        Date date1 = new Date(dateFromNumber[2], dateFromNumber[1], dateFromNumber[0]);
        Date date2 = new Date(dateToNumber[2], dateToNumber[1], dateToNumber[0]);
        long totalHours = date2.getTime() - date1.getTime();
        int dayCount = (int) totalHours / ONE_DAY / HOURS_PER_DAY;
        return "";

    }
}
