package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int date = 0;
        int worker = 1;
        int hours = 2;
        int money = 3;
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate beginPeriod = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate endPeriod = LocalDate.parse(dateTo, dateTimeFormatter);
        stringBuilder.append("Report for period ")
                    .append(dateFrom).append(" - ")
                    .append(dateTo)
                    .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] partsOfData = datum.split("\\s");
                if (partsOfData[worker].equals(name)
                            && !LocalDate.parse(partsOfData[date],
                            dateTimeFormatter).isAfter(endPeriod)
                            && !LocalDate.parse(partsOfData[date],
                            dateTimeFormatter).isBefore(beginPeriod)) {
                    salary += (Integer.parseInt(partsOfData[hours])
                                * Integer.parseInt(partsOfData[money]));
                }
            }
            stringBuilder.append(name)
                        .append(" - ")
                        .append(salary)
                        .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
