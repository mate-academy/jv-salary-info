package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        LocalDate dateFirst = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateSecond = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String name : names) {
            int result = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(name)) {
                    String[] currentData = data[i].split(" ");
                    LocalDate localDate = LocalDate.parse(currentData[0], DATE_FORMAT);
                    if (localDate.isAfter(dateFirst)
                            && localDate.isBefore(dateSecond)
                            || localDate.equals(dateFirst)
                            || localDate.equals(dateSecond)) {
                        result += Integer.parseInt(currentData[2])
                                * Integer.parseInt(currentData[3]);
                    }
                }
            }
            builder.append(name).append(" - ").append(String.valueOf(result)).append("\n");
        }
        return builder.toString().trim();
    }
}
