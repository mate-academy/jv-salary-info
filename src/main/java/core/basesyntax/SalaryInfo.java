package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int HOURS_WORKED = 2;
    private static final int HOURLY_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;
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
                startDate = LocalDate.parse(dateFrom, DT_FORMATTER);
                endDate = LocalDate.parse(dateTo, DT_FORMATTER);
                workDate = LocalDate.parse(splittedData[INDEX_OF_DATE], DT_FORMATTER);

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
