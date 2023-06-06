package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int firstWord = 0;
    static final int secondWord = 1;
    static final int thirdWord = 2;
    static final int forthWord = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);

        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String dataRow : data) {
                String[] dataRowSplit = dataRow.split(" ");
                LocalDate workingDate = LocalDate.parse(dataRowSplit[firstWord], formatter);
                if (name.equals(dataRowSplit[secondWord]) && workingDate.isAfter(dateFromLocal)
                        && (workingDate.isBefore(dateToLocal)
                        || workingDate.isEqual(dateToLocal))) {
                    sum += Integer.parseInt(dataRowSplit[thirdWord])
                            * Integer.parseInt(dataRowSplit[forthWord]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return result.toString();
    }
}
