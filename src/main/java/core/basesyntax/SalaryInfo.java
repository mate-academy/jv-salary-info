package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataString : data) {
                String[] dataSplitArray = dataString.split(" ");
                if (dataCheck(dataSplitArray, name, dateFrom, dateTo)) {
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

    public boolean dataCheck(String[] dataSplitArray, String name, String dateFrom, String dateTo) {
        boolean dataResult = false;
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);
        LocalDate date = LocalDate.parse(dataSplitArray[DATE_INDEX], FORMATTER);
        if (!date.isBefore(beginningDate)
                && !date.isAfter(endingDate)
                && dataSplitArray[NAME_INDEX].equals(name)) {
            dataResult = true;
        }
        return dataResult;
    }
}

