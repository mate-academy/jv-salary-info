package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder message = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo);

        for (String name : names) {
            int sum = 0;
            for (String strData : data) {
                String[] parts = strData.split(" ");
                LocalDate date = LocalDate.parse(parts[0], formatter);
                boolean dateInRange = (date.isEqual(parseDateFrom) || date.isAfter(parseDateFrom))
                        && (date.isEqual(parseDateTo) || date.isBefore(parseDateTo));
                boolean nameMatches = name.equals(parts[1]);
                if (dateInRange && nameMatches) {
                    sum += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                }
            }
            message.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return message.toString();
    }
}
