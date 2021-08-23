package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DAY = 0;
    private static final int NAME = 1;
    private static final int HOURS_OF_WORK = 2;
    private static final int MONEY_PER_HOUR = 3;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitedData;
        LocalDate dayFromData;
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate formattedDateTo = LocalDate.parse(dateTo,formatter);
        int[] salaries = new int[names.length];
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder result = stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String datum : data) {
            splitedData = datum.split(" ");
            dayFromData = LocalDate.parse(splitedData[DAY], formatter);
            if ((dayFromData.isBefore(formattedDateTo)
                    && dayFromData.isAfter(formattedDateFrom))
                    || dayFromData.isEqual(formattedDateTo)
                    || dayFromData.isEqual(formattedDateFrom)) {
                for (int j = 0; j < names.length; j++) {
                    if (splitedData[NAME].equals(names[j])) {
                        salaries[j] += Integer.parseInt(splitedData[HOURS_OF_WORK])
                                * Integer.parseInt(splitedData[MONEY_PER_HOUR]);
                    }
                }
            }
        }
        for (int i = 0; i < salaries.length; i++) {
            result.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaries[i]);
        }
        return result.toString();
    }
}
