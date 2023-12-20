package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String currentName : names) {
            int totalIncome = 0;
            for (String day : data) {
                String[] currentDayData = day.split(" ");
                LocalDate currentDate = LocalDate.parse(currentDayData[DATE], FORMATTER);
                if ((currentDate.equals(startDate) || currentDate.equals(endDate)
                        || (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)))
                        && (currentName.equals(currentDayData[NAME]))) {
                    int hours = Integer.parseInt(currentDayData[HOURS]);
                    int income = Integer.parseInt(currentDayData[INCOME]);
                    totalIncome += hours * income;
                }
            }
            builder.append(System.lineSeparator())
                    .append(currentName)
                    .append(" - ")
                    .append(totalIncome);
        }
        return builder.toString();
    }
}
