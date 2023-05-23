package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        for (String name : names) {
            int earnedMoney = 0;
            for (String entry : data) {
                String[] entryParts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(entryParts[0], dateFormatter);
                if (entryParts[1].equals(name) && !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)) {
                    int hours = Integer.parseInt(entryParts[2]);
                    int hourlyRate = Integer.parseInt(entryParts[3]);
                    earnedMoney += hours * hourlyRate;
                }
            }
            report.append(name).append(" - ").append(earnedMoney).append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
