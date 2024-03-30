package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int LOCAL_DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int result;
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result = 0;
            for (int j = 0; j < data.length; j++) {
                String[] infoArray = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(infoArray[LOCAL_DATA_INDEX], formatter);
                if (names[i].equals(infoArray[NAME_INDEX])
                        && !localDate.isBefore(fromDate) && !localDate.isAfter(toDate)) {
                    result += Integer.parseInt(infoArray[WORKED_HOURS_INDEX])
                            * Integer.parseInt(infoArray[HOURLY_RATE_INDEX]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(result);
        }
        System.out.println(builder);
        return builder.toString();
    }
}
