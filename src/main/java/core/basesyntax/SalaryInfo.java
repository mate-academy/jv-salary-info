package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLD = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToLD = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int sum = 0;
            for (String dataEntry : data) {
                String[] entryArray = dataEntry.split(" ");
                LocalDate entryDate = LocalDate.parse(entryArray[0], DATE_FORMAT);
                if ((entryDate.isEqual(dateFromLD) || entryDate.isAfter(dateFromLD))
                        && (entryDate.isBefore(dateToLD) || entryDate.isEqual(dateToLD))
                        && entryArray[1].equals(name)) {
                    sum += Integer.parseInt(entryArray[2]) * Integer.parseInt(entryArray[3]);
                }
            }
            stringBuilder.append(sum);
        }
        return stringBuilder.toString();
    }
}
