package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateF = LocalDate.parse(dateFrom, formatter);
        LocalDate dateT = LocalDate.parse(dateTo, formatter);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            Integer earning = 0;
            for (String row: data) {
                String[] rowParts = row.split(" ");

                if (rowParts[1].equals(name)) {
                    LocalDate workDate = LocalDate.parse(rowParts[0], formatter);

                    if (!workDate.isBefore(dateF) && !workDate.isAfter(dateT)) {
                        earning += Integer.parseInt(rowParts[2]) * Integer.parseInt(rowParts[3]);
                    }
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(earning);
        }
        return stringBuilder.toString();
    }
}
