package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        int[] salaryArr = new int [names.length];

        StringBuilder header = new StringBuilder("Report for period ");
        header.append(parsedDateFrom.format(FORMATTER))
                .append(" - ")
                .append(parsedDateTo.format(FORMATTER));

        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                String[] splitData = data[i].split(" ");
                LocalDate dataDate = LocalDate.parse(splitData[0], FORMATTER);
                int parsedHours = Integer.parseInt(splitData[2]);
                int parsedMoney = Integer.parseInt(splitData[3]);

                if ((dataDate.isAfter(parsedDateFrom) || dataDate.isEqual(parsedDateFrom))
                        && (dataDate.isBefore(parsedDateTo) || dataDate.isEqual(parsedDateTo))) {
                    if (names[j].equals(splitData[1])) {
                        salaryArr[j] = salaryArr[j] + parsedHours * parsedMoney;
                    }
                }
            }
            header.append(System.lineSeparator())
                    .append(names[j])
                    .append(" - ")
                    .append(salaryArr[j]);
        }

        return header.toString();
    }
}
