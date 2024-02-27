package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String dataString : data) {
                String[] dataSplitArray = dataString.split(" ");
                LocalDate date = LocalDate.parse(dataSplitArray[DATE_INDEX], FORMATTER);
                if ((date.isAfter(beginningDate) || date.isEqual(beginningDate))
                        && (date.isBefore(endingDate) || date.isEqual(endingDate))
                        && dataSplitArray[NAME_INDEX].equals(name)) {
                    int hour = Integer.valueOf(dataSplitArray[HOUR_INDEX]);
                    int income = Integer.valueOf(dataSplitArray[INCOME_INDEX]);
                    int sum = hour * income;
                    salary = salary + sum;
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        String resultToString = result.toString();
        int index = resultToString.lastIndexOf(System.lineSeparator());
        return resultToString.substring(0, index);
    }
}
