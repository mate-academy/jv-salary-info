package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int HOURS_WORKED = 2;
    private static final int HOURLY_RATE = 3;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate workDate;

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int money = 0;

            for (String element : data) {
                String[] splittedData = element.split(" ");
                workDate = LocalDate.parse(splittedData[INDEX_OF_DATE], DATE_TIME_FORMATTER);

                if (name.equals(splittedData[INDEX_OF_NAME])
                        && workDate.isAfter(startDate.minusDays(1))
                        && workDate.isBefore(endDate.plusDays(1))) {
                    money += Integer.parseInt(splittedData[HOURS_WORKED])
                            * Integer.parseInt(splittedData[HOURLY_RATE]);
                }
            }
            builder.append(name).append(" - ")
                    .append(money).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
