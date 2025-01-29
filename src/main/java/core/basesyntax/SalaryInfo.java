package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder infoBuilder = new StringBuilder("Report for period ");
        infoBuilder.append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateFromFormat = LocalDate.parse(dateFrom, FORMAT);
        LocalDate dateToFormat = LocalDate.parse(dateTo, FORMAT);
        for (String name : names) {
            infoBuilder.append(System.lineSeparator()).append(name);
            int sumSalaryTemp = 0;
            for (String dataString : data) {
                String[] dataStringSplit = dataString.split(" ");
                if (dataStringSplit[1].equals(name)) {
                    LocalDate localDate = LocalDate.parse(dataStringSplit[0], FORMAT);
                    if (!localDate.isBefore(dateFromFormat) && !localDate.isAfter(dateToFormat)) {
                        sumSalaryTemp += Integer.parseInt(dataStringSplit[2])
                                * Integer.parseInt(dataStringSplit[3]);
                    }
                }
            }
            infoBuilder.append(" - ").append(sumSalaryTemp);
        }
        return infoBuilder.toString();
    }
}
