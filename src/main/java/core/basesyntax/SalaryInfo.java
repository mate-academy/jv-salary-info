package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLD = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate dateToLD = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        List<String> dataCut = new ArrayList<>();
        for (String dataEntry : data) {
            LocalDate entryDate = LocalDate.parse(dataEntry
                    .replaceAll("\\s\\w+\\s\\d+\\s\\d+$", ""),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (entryDate.isEqual(dateFromLD) || entryDate.isAfter(dateFromLD)
                    && entryDate.isBefore(dateToLD) || entryDate.isEqual(dateToLD)) {
                dataCut.add(dataEntry);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int sum = 0;
            for (String dataEntry : dataCut) {
                if (dataEntry.contains(name)) {
                    String[] entryArray = dataEntry.split(" ");
                    sum += Integer.parseInt(entryArray[entryArray.length - 1])
                            * Integer.parseInt(entryArray[entryArray.length - 2]);
                }
            }
            stringBuilder.append(sum);
        }
        return stringBuilder.toString();
    }
}
