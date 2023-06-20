package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sum = new int[names.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date1 = LocalDate.parse(dateFrom, formatter);
        LocalDate date2 = LocalDate.parse(dateTo,formatter);
        for (int i = 0; i < data.length; i++) {
            String[] dataArray = data[i].split(" ");
            LocalDate date3 = LocalDate.parse(dataArray[0], formatter);
            if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                for (int k = 0; k < names.length; k++) {
                    if (names[k].equals(dataArray[1])) {
                        sum[k] += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            result.append(names[i] + " - " + sum[i] + System.lineSeparator());
        }
        return result.toString().trim();
    }
}
