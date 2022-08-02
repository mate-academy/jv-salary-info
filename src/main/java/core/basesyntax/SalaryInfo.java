package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int currentSalary = 0;
            for (String currentData : data) {
                String[] splitValues = currentData.split(" ");
                LocalDate currentDate = LocalDate.parse(splitValues[0], FORMATTER);
                String currentName = splitValues[1];
                int hoursCounter = Integer.parseInt(splitValues[2]);
                int cashPerHour = Integer.parseInt(splitValues[3]);

                if (currentName.equals(name)
                        && (currentDate.equals(fromDate) || currentDate.isAfter(fromDate))
                        && (currentDate.equals(toDate) || currentDate.isBefore(toDate))) {
                    currentSalary += hoursCounter * cashPerHour;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(currentSalary);
        }
        return builder.toString();
    }
}
