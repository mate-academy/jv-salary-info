package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localTo = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator())
                .append(String.join(" - 0" + System.lineSeparator(), names))
                .append(" - 0")
                .append(System.lineSeparator());
        for (String dataUnit : data) {
            String[] dataSeparated = dataUnit.split(" ");
            LocalDate dataDate = LocalDate.parse(dataSeparated[0], formatter);
            if (dataDate.isAfter(localFrom)
                    && dataDate.isBefore(localTo)
                    || dataDate.isEqual(localFrom)
                    || dataDate.isEqual(localTo)) {
                for (String name : names) {
                    if (dataSeparated[1].equals(name)) {
                        int startIndex =
                                result.indexOf(dataSeparated[1]) + dataSeparated[1].length() + 3;
                        int endIndex = result.indexOf(System.lineSeparator(), startIndex);
                        result.replace(
                                startIndex,
                                endIndex,
                                String.valueOf(
                                        Integer.parseInt(dataSeparated[2])
                                        * Integer.parseInt(dataSeparated[3])
                                        + Integer.parseInt(result.substring(startIndex, endIndex)
                                        )));
                        break;
                    }
                }
            }
        }
        return result.substring(0, result.lastIndexOf(System.lineSeparator()));
    }
}
