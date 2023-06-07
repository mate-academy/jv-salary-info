package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int FIRST_WORD = 0;
    static final int SECOND_WORD = 1;
    static final int THIRD_WORD = 2;
    static final int FORTH_WORD = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String dataRow : data) {
                String[] dataRowSplit = dataRow.split(" ");
                LocalDate workingDate = LocalDate.parse(dataRowSplit[FIRST_WORD], FORMATTER);
                if (name.equals(dataRowSplit[SECOND_WORD]) && workingDate.isAfter(dateFromLocal)
                        && (workingDate.isBefore(dateToLocal)
                        || workingDate.isEqual(dateToLocal))) {
                    sum += Integer.parseInt(dataRowSplit[THIRD_WORD])
                            * Integer.parseInt(dataRowSplit[FORTH_WORD]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return result.toString();
    }
}
