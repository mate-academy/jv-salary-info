package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;
    private static final String DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMAT);
        LocalDate to = LocalDate.parse(dateTo, FORMAT);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(DASH)
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int amount = 0;
            for (String dateElement : data) {
                if (dateElement.contains(name)) {
                    String[] distributedDataToTheArray = dateElement.split(" ");
                    LocalDate date = LocalDate.parse(
                            distributedDataToTheArray[INDEX_OF_DATE], FORMAT);
                    int hours = Integer.parseInt(
                            distributedDataToTheArray[INDEX_OF_WORKING_HOURS]);
                    int salaryByHour = Integer.parseInt(
                            distributedDataToTheArray[INDEX_OF_INCOME_PER_HOUR]);
                    if ((date.isAfter(from) || date.equals(from))
                            && (date.equals(to) || date.isBefore(to))) {
                        amount += hours * salaryByHour;
                    }
                }
            }
            stringBuilder.append(name)
                    .append(DASH)
                    .append(amount)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
