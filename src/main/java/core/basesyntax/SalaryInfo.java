package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            resultStr.append(System.lineSeparator()).append(name).append(" - ");
            int sum = 0;
            for (String d : data) {
                String[] splitData = d.split(" ");
                if (splitData[1].equals(name) && isRelevantDate(splitData[0], dateFrom, dateTo)) {
                    sum += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            resultStr.append(sum);
        }
        return resultStr.toString();
    }

    private boolean isRelevantDate(String dateStr, String dateFromStr, String dateToStr) {
        LocalDate date = LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
        LocalDate dateFrom = LocalDate.parse(dateFromStr, DATE_TIME_FORMATTER);
        LocalDate dateTo = LocalDate.parse(dateToStr, DATE_TIME_FORMATTER);
        return date.isAfter(dateFrom) && date.isBefore(dateTo)
                || date.isEqual(dateFrom) || date.isEqual(dateTo);
    }
}
