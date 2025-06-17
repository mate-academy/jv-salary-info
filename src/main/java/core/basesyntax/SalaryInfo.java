package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        return "Report for period "
                + dateFrom + " - " + dateTo
                + getSalaryForWorkers(names, data, dateFrom, dateTo);
    }

    public String getSalaryForWorkers(String[] names, String[] data,
                                      String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        StringBuilder result = new StringBuilder();

        for (String name : names) {
            int total = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate recordDate = LocalDate.parse(parts[0], formatter);
                String recordName = parts[1];

                if (recordName.equals(name)
                        && !recordDate.isBefore(startDate)
                        && !recordDate.isAfter(endDate)) {
                    int hours = Integer.parseInt(parts[2]);
                    int price = Integer.parseInt(parts[3]);
                    total += hours * price;
                }
            }

            result.append("\r\n").append(name).append(" - ").append(total);
        }
        return result.toString();
    }
}
