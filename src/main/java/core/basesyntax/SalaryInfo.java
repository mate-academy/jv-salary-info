package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dayFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dayTo = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int incomePerHour;
            int hoursPerDay;
            int dayIncome = 0;
            for (String dailyData : data) {
                String[] splittedData = dailyData.split(" ");
                ;
                LocalDate workingDay = LocalDate.parse(splittedData[DATE_INDEX], formatter);
                ;
                if (dailyData.contains(name) && isDayInPeriod(workingDay, dayFrom, dayTo)) {
                    incomePerHour = Integer.parseInt(splittedData[INCOME_INDEX]);
                    hoursPerDay = Integer.parseInt(splittedData[HOURS_INDEX]);
                    dayIncome += incomePerHour * hoursPerDay;
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(dayIncome);
        }
        return result.toString();
    }

    private boolean isDayInPeriod(LocalDate thisDate, LocalDate dateFrom, LocalDate dateTo) {
        return thisDate.isEqual(dateTo) || thisDate.isAfter(dateFrom) && thisDate.isBefore(dateTo);
    }
}
