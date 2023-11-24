package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_RATE = 3;
    private static final int ONE_DAY = 1;
    private static final String DIVIDER = " - ";
    private static final String STRING_SPLITTER = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateOfStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateOfEnd = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(DIVIDER).append(dateTo);

        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(DIVIDER);
            int countedMoney = 0;

            for (String input : data) {
                if (input != null) {
                    String splitName = input.split(STRING_SPLITTER)[INDEX_OF_NAME];
                    String splitDate = input.split(STRING_SPLITTER)[INDEX_OF_DATE];
                    LocalDate date = LocalDate.parse(splitDate, formatter);

                    int hours = Integer.parseInt(input.split(STRING_SPLITTER)[INDEX_OF_HOURS]);
                    int rate = Integer.parseInt(input.split(STRING_SPLITTER)[INDEX_OF_RATE]);

                    if (name.equals(splitName) && date.minusDays(ONE_DAY).isBefore(dateOfEnd)
                                               && date.plusDays(ONE_DAY).isAfter(dateOfStart)) {
                        countedMoney += hours * rate;
                    }
                }
            }
            builder.append(countedMoney);
        }
        return builder.toString();
    }
}
