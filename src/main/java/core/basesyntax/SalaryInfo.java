package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        int sumSalary = 0;
        for (String name: names) {
            for (String dataElement: data) {
                String[] splitData = dataElement.split(" ");
                LocalDate dataDate = LocalDate.parse(splitData[DATE_INDEX], formatter);
                if (splitData[NAME_INDEX].equals(name) && (dataDate.isAfter(startDate)
                        || dataDate.isEqual(startDate)) && (dataDate.isBefore(endDate)
                        || dataDate.isEqual(endDate))) {
                    sumSalary += Integer.parseInt(splitData[HOUR_INDEX])
                            * Integer.parseInt(splitData[INCOME_INDEX]);
                }
            }
            result
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(sumSalary);
            sumSalary = 0;
        }
        return result.toString();
    }
}
