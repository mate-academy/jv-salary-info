package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder reporter = new StringBuilder();
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate secondDate = LocalDate.parse(dateTo, formatter);
        reporter.append("Report for period ").append(
            firstDate.format(formatter)).append(" - ").append(
            secondDate.format(formatter)).append("\n");
        for (int i = 0; i < names.length; i++) {
            int totalAmount = 0;
            for (String row : data) {
                String[] record = row.trim().split("\\s");
                LocalDate recordDate = LocalDate.parse(record[0], formatter);
                String recordName = record[1];
                int amountPerHour = Integer.parseInt(record[3]);
                int amountHour = Integer.parseInt(record[2]);
                if (recordDate.compareTo(firstDate) >= 0
                        && recordDate.compareTo(secondDate) <= 0
                        && recordName.equals(names[i])) {
                    totalAmount += amountPerHour * amountHour;
                }
            }
            reporter.append(names[i]).append(" - ").append(totalAmount);
            if (i < names.length - 1) {
                reporter.append("\n");
            }
        }
        return reporter.toString();
    }
}
