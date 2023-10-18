package core.basesyntax;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_RATE = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);

        String[] filteredData = filterDataByDate(data, dateFrom, dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int countedMoney = 0;
            for (String input : filteredData) {
                if (input != null && name.equals(input.split(" ")[INDEX_OF_NAME])) {
                    countedMoney += Integer.parseInt(input.split(" ")[INDEX_OF_HOURS])
                                   * Integer.parseInt(input.split(" ")[INDEX_OF_RATE]);
                }
            }
            builder.append(countedMoney);
        }
        return builder.toString();
    }

    private String[] filterDataByDate(String[] data, String dateFrom, String dateTo) {
        LocalDate dateOfStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateOfEnd = LocalDate.parse(dateTo, formatter);

        String[] dates = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            dates[i] = data[i].split(" ")[INDEX_OF_DATE];
        }

        String[] result = new String[data.length];
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            LocalDate date = LocalDate.parse(dates[i], formatter);
            Period spanToEnd = Period.between(date, dateOfEnd);
            Period spanToStart = Period.between(dateOfStart, date);

            if (spanToEnd.getDays() >= 0 && spanToStart.getDays() >= 0) {
                result[index] = data[i];
                index++;
            }
        }
        return result;
    }
}
