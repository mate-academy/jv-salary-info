package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private StringBuilder builder = new StringBuilder();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateFirst = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateSecond = LocalDate.parse(dateTo, DATE_FORMAT);
        int result = 0;

        for (String dat : data) {
            String[] currentData = dat.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (currentData[i].contains(names[i])) {
                    LocalDate dayDate = LocalDate.parse(currentData[0], DATE_FORMAT);
                    if (dayDate.isAfter(dateFirst)
                            || dayDate.isBefore(dateSecond)
                            || dayDate.equals(dateFirst)
                            || dayDate.equals(dateSecond)) {
                        result += Integer.parseInt(currentData[2])
                                * Integer.parseInt(currentData[3]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            builder.append("REport for period ").append(dateFrom)
                    .append(" - ").append(dateTo).append("\n").append(names[i]).append(result);
        }
        return builder.toString();
    }
}
