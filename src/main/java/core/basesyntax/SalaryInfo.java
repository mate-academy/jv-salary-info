package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
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
                if (partsOfData[1].equals(name)
                            && LocalDate.parse(partsOfData[0],
                            dateTimeFormatter).isBefore(endPeriod.plusDays(1))
                            && LocalDate.parse(partsOfData[0],
                            dateTimeFormatter).isAfter(beginPeriod.minusDays(1))) {
                    salary += (Integer.parseInt(partsOfData[2]) * Integer.parseInt(partsOfData[3]));
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
