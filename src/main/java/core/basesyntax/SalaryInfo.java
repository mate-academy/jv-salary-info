package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final int index0 = 0;
    private final int index1 = 1;
    private final int index2 = 2;
    private final int index3 = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLD = LocalDate.parse(dateFrom,formatter).minusDays(1);
        LocalDate dateToLD = LocalDate.parse(dateTo,formatter).plusDays(1);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int income = 0;
            for (String dataForSplit : data) {
                String[] dataArray = dataForSplit.split(" ");
                LocalDate date = LocalDate.parse(dataArray[index0],formatter);
                if (name.equals(dataArray[index1]) && dateFromLD.isBefore(date)
                        && dateToLD.isAfter(date)) {
                    income = income + Integer.parseInt(dataArray[index3])
                            * Integer.parseInt(dataArray[index2]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(income);
        }
        return builder.toString();
    }
}
