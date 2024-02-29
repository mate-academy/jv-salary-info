package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final int DAY_BEFORE_OR_AFTER = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        String endResult = calculateSalary(names, data, beginningDate, endingDate, result);
        return endResult;
    }

    public String calculateSalary(String[] names, String[] data, LocalDate beginningDate,
                                  LocalDate endingDate, StringBuilder result) {
        for (String name : names) {
            int salary = 0;
            for (String dataString : data) {
                String[] dataSplitArray = dataString.split(" ");
                LocalDate date = LocalDate.parse(dataSplitArray[DATE_INDEX], FORMATTER);
                if (date.isAfter(beginningDate.minusDays(DAY_BEFORE_OR_AFTER))
                        && date.isBefore(endingDate.plusDays(DAY_BEFORE_OR_AFTER))
                        && dataSplitArray[NAME_INDEX].equals(name)) {
                    int hour = Integer.parseInt(dataSplitArray[HOUR_INDEX]);
                    int income = Integer.parseInt(dataSplitArray[INCOME_INDEX]);
                    salary += hour * income;
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return result.toString();
    }
}
