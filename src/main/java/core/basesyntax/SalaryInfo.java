package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int INDEX_DATE = 0;

    private static final int INDEX_NAME = 1;

    private static final int INDEX_HOURS = 2;

    private static final int INDEX_MONEY_PER_HOUR = 3;

    private static final int INDEX_OF_BEGIN_ARRAY = 0;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            String[] dataCurrentNameArray;
            int moneyEarned = 0;
            for (String dataCurrentName : data) {
                dataCurrentNameArray = dataCurrentName.split(" ");
                if (dataCurrentNameArray[INDEX_NAME].equals(name)
                        && LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                        .compareTo(LocalDate.parse(dateFrom, FORMATTER)) >= 0
                        && LocalDate.parse(dataCurrentNameArray[INDEX_DATE], FORMATTER)
                        .compareTo(LocalDate.parse(dateTo, FORMATTER)) <= 0) {
                    moneyEarned += Integer.parseInt(dataCurrentNameArray[INDEX_HOURS])
                            * Integer.parseInt(dataCurrentNameArray[INDEX_MONEY_PER_HOUR]);
                }
            }
            builder.append(name).append(" - ").append(moneyEarned).append(System.lineSeparator());
        }
        return builder.substring(INDEX_OF_BEGIN_ARRAY, builder.toString().length() - 2);
    }
}
