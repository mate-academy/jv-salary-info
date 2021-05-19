package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String initialString = "Report for period " + dateFrom + " - " + dateTo + "\n";
        StringBuilder stringBuilder = new StringBuilder(initialString);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String line : data) {
                String[] entities = line.split(" ");
                LocalDate dateWorked = LocalDate.parse(entities[0], DATE_TIME_FORMATTER);
                if (name.equals(entities[1])
                        && !dateWorked.isBefore(startDate)
                        && !dateWorked.isAfter(endDate)) {
                    int hoursWorked = Integer.parseInt(entities[2]);
                    int ratePerHour = Integer.parseInt(entities[3]);
                    totalSalary += hoursWorked * ratePerHour;
                }
            }
            String resultString = name + " - " + totalSalary + "\n";
            stringBuilder.append(resultString);
        }
        return stringBuilder.toString().trim();
    }
}
