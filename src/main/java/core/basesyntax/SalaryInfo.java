package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER).minus(1, ChronoUnit.DAYS);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER).plus(1, ChronoUnit.DAYS);
        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String workData : data) {
                String[] split = workData.split(" ");
                LocalDate workDay = LocalDate.parse(split[0], FORMATTER);
                if (name.equals(split[1]) && workDay.isAfter(localDateFrom)
                        && workDay.isBefore(localDateTo)) {
                    int hours = Integer.parseInt(split[2]);
                    int incomePerHour = Integer.parseInt(split[3]);
                    moneyEarned += hours * incomePerHour;
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(moneyEarned);
        }
        return sb.toString();
    }
}
