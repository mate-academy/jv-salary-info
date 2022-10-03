package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int countMoney = 0;
            for (String info : data) {
                String[] parsedInfo = info.split(" ");
                LocalDate infoDate = LocalDate.parse(parsedInfo[0], formatter);
                if (parsedInfo[1].equals(name)
                        && compareData(parsedDateFrom, parsedDateTo, infoDate)) {
                    countMoney += Integer.parseInt(parsedInfo[2]) * Integer.parseInt(parsedInfo[3]);
                }
            }
            stringBuilder
                    .append(name)
                    .append(" - ")
                    .append(countMoney)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    public boolean compareData(LocalDate dateFrom, LocalDate dateTo, LocalDate comparedDate) {
        return (dateFrom.isBefore(comparedDate) || dateFrom.equals(comparedDate))
                && (dateTo.isAfter(comparedDate) || dateTo.equals(comparedDate));
    }
}
