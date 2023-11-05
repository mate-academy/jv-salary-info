package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION = 0;
    private static final int QUANTITY_POSITION = 2;
    private static final int AMOUNT_POSITION = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name: names) {
            int totalResourses = 0;
            for (String dataLine: data) {
                if (!dataLine.contains(name)) {
                    continue;
                }
                String[] dataArray = dataLine.split(" ");
                if (LocalDate
                        .parse(dataArray[DATE_POSITION], formatter)
                        .compareTo(LocalDate.parse(dateTo, formatter)) > 0
                        || LocalDate
                            .parse(dataArray[DATE_POSITION], formatter)
                            .compareTo(LocalDate.parse(dateFrom, formatter)) < 0) {
                    continue;
                }
                totalResourses += Integer.parseInt(dataArray[QUANTITY_POSITION])
                        * Integer.parseInt(dataArray[AMOUNT_POSITION]);
            }
            builder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalResourses);
        }
        return builder.toString();
    }
}



