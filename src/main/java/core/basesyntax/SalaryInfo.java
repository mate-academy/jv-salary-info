package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        int[] sum = new int[names.length];
        LocalDate beginningOfPeriod = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endOfPeriod = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataArray = data[j].split(" ");
                LocalDate workingDay = LocalDate.parse(dataArray[0], FORMATTER);
                if (!workingDay.isBefore(beginningOfPeriod) && !workingDay.isAfter(endOfPeriod)) {
                    if (names[i].equals(dataArray[1])) {
                        sum[i] += Integer.parseInt(dataArray[2])
                                * Integer.parseInt(dataArray[3]);
                    }
                }
            }
            result.append(names[i] + " - " + sum[i] + System.lineSeparator());
        }

        return result.toString().trim();
    }
}
