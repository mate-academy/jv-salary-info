package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int earnedMoney = 0;
            for (String part : data) {
                String[] parts = part.split(" ");
                LocalDate entryDate = LocalDate.parse(parts[0], dateFormatter);
                String employeeName = parts[1];
                int workingHours = Integer.parseInt(parts[2]);
                int incomePerHour = Integer.parseInt(parts[3]);

                if (employeeName.equals(name) && !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)) {
                    earnedMoney += workingHours * incomePerHour;
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(earnedMoney)
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
