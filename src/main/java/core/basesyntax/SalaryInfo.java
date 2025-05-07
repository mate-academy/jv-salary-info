package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            stringBuilder.append(System.lineSeparator());
            for (int j = 0; j < data.length; j++) {
                String[] table = data[j].split(" ");
                if (names[i].equals(table[1])) {
                    LocalDate parseFileDate = LocalDate.parse(table[0], FORMATTER);

                    if (fromDate.isEqual(parseFileDate)
                            || toDate.isEqual(parseFileDate)
                            || (parseFileDate.isAfter(fromDate)
                            & parseFileDate.isBefore(toDate))) {
                        sum += Integer.parseInt(table[2]) * Integer.parseInt(table[3]);
                    }
                }
            }
            stringBuilder.append(names[i]).append(" - ").append(sum);
        }
        return stringBuilder.toString();
    }
}
