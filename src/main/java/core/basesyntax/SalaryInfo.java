package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_CURRENT_NAME = 1;
    private static final int INDEX_OF_WORKING_HOUSE = 2;
    private static final int INDEX_OF_PRICE_PER_HOUR = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private int workingHour;
    private int pricePerHour;
    private String currentName;
    private int salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator());

        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateStop = LocalDate.parse(dateTo, formatter);

        for (String name:names) {
            for (int i = 0; i < data.length; i++) {
                String[] words = data[i].split(" ");
                final LocalDate date = LocalDate.parse(words[INDEX_OF_DATE], formatter);
                currentName = words[INDEX_OF_CURRENT_NAME];
                workingHour = Integer.parseInt(words[INDEX_OF_WORKING_HOUSE]);
                pricePerHour = Integer.parseInt(words[INDEX_OF_PRICE_PER_HOUR]);
                if (date.isAfter(dateStart.minusDays(1))
                        && date.isBefore(dateStop.plusDays(1)) && currentName.equals(name)) {
                    salary += workingHour * pricePerHour;
                }
            }
            builder.append(name).append(" - ").append(salary).append(System.lineSeparator());
            salary = 0;
        }

        return builder.substring(0, builder.length() - 2);
    }
}
