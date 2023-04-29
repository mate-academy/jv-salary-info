package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate localDateTo = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(DASH)
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int sum = 0;
            for (String test : data) {
                if (test == null) {
                    continue;
                }
                String[] parts = test.split(SPACE);
                if (parts[1].equals(name)) {
                    String date = parts[0];
                    LocalDate localDate = LocalDate.parse(date,
                            DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    if (localDate.compareTo(localDateFrom) >= 0
                            && localDate.compareTo(localDateTo) <= 0) {
                        int hours = Integer.parseInt(parts[2]);
                        int incomePerHour = Integer.parseInt(parts[3]);
                        sum += hours * incomePerHour;
                    }
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(sum)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
