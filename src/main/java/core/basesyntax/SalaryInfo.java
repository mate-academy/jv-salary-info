package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;

public class SalaryInfo {
    private static final int TIME_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static final int DAY_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo).plusDays(1);
        int[] summarySalaryInfo = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] timeless = data[i].split(" ");
            int indexOf = Arrays.asList(names).indexOf(timeless[NAME_INDEX]);
            LocalDate dateNow = parseDate(timeless[TIME_INDEX]);
            if (dateNow.isAfter(startDate) && dateNow.isBefore(endDate)) {
                summarySalaryInfo[indexOf] = summarySalaryInfo[indexOf]
                        + (Integer.parseInt(timeless[HOUR_INDEX]) * Integer
                        .parseInt(timeless[MONEY_PER_HOUR_INDEX]));
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(summarySalaryInfo[i]);
        }
        return builder.toString();
    }

    private LocalDate parseDate(String date) {
        LocalDate dateResult = LocalDate.of(Integer.parseInt(date.split("[.]")[YEAR_INDEX]),
                Integer.parseInt(date.split("[.]")[MONTH_INDEX]),Integer.parseInt(date
                        .split("[.]")[DAY_INDEX]));
        return dateResult;
    }
}
