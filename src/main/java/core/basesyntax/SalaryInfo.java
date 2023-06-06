package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);

        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String dataRow : data) {
                String[] dataRowSplit = dataRow.split(" ");
                LocalDate workingDate = LocalDate.parse(dataRowSplit[0], formatter);
                if (name.equals(dataRowSplit[1]) && workingDate.isAfter(dateFromLocal)
                        && (workingDate.isBefore(dateToLocal)
                        || workingDate.isEqual(dateToLocal))) {
                    sum += Integer.parseInt(dataRowSplit[2])
                            * Integer.parseInt(dataRowSplit[3]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return result.toString();
    }
}
