package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String dataRow : data) {
                String[] dataRowSplit = dataRow.split(" ");
                LocalDate workingDate = LocalDate.parse(dataRowSplit[DATE_INDEX], FORMATTER);
                if (name.equals(dataRowSplit[NAME_INDEX]) && !workingDate.isBefore(dateFromLocal)
                        && !workingDate.isAfter(dateToLocal)) {
                    sum += Integer.parseInt(dataRowSplit[WORKING_HOURS_INDEX])
                            * Integer.parseInt(dataRowSplit[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return result.toString();
    }
}
