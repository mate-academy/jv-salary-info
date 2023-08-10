package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String n : names) {
            resultStr.append("\n").append(n).append(" - ");
            int sum = 0;
            for (String d : data) {
                String[] splitData = d.split(" ");
                if (splitData[1].equals(n) && isRelevantDate(splitData[0], dateFrom, dateTo)) {
                    sum += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            resultStr.append(sum);
        }
        return resultStr.toString();
    }

    private boolean isRelevantDate(String dateStr, String dateFromStr, String dateToStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate dateFrom = LocalDate.parse(dateFromStr, formatter);
        LocalDate dateTo = LocalDate.parse(dateToStr, formatter);
        return date.isAfter(dateFrom) && date.isBefore(dateTo)
                || date.isEqual(dateFrom) || date.isEqual(dateTo);
    }
}
