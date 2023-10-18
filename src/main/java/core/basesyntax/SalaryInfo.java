package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_RATE = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateOfStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateOfEnd = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int countedMoney = 0;

            for (String input : data) {
                if (input != null) {
                    String splitName = input.split(" ")[INDEX_OF_NAME];
                    String splitDate = input.split(" ")[INDEX_OF_DATE];
                    LocalDate date = LocalDate.parse(splitDate, formatter);

                    int hours = Integer.parseInt(input.split(" ")[INDEX_OF_HOURS]);
                    int rate = Integer.parseInt(input.split(" ")[INDEX_OF_RATE]);

                    if (name.equals(splitName) && date.minusDays(1).isBefore(dateOfEnd)
                                               && date.plusDays(1).isAfter(dateOfStart)) {
                        countedMoney += hours * rate;
                    }
                }
            }
            builder.append(countedMoney);
        }
        return builder.toString();
    }
}
