package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        LocalDate localDateFrom = parseDate(dateFrom);
        LocalDate localDateTo = parseDate(dateTo);

        for (String username: names) {
            int money = 0;
            for (String jobData: data) {
                if (jobData.contains(username)) {
                    String[] parts = jobData.split(" ");
                    LocalDate workDate = parseDate(parts[DATE_INDEX]);
                    if (ifDateInStatisticRange(workDate,localDateFrom,localDateTo)) {
                        money += calculateMoneyPerHour(parts[WORKING_HOURS_INDEX],
                            parts[MONEY_PER_HOUR_INDEX]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(username).append(" - ").append(money);
        }
        return result.toString();
    }

    private boolean ifDateInStatisticRange(LocalDate workDate, LocalDate dateFrom,
                LocalDate dateTo) {
        return (workDate.isAfter(dateFrom) || workDate.isEqual(dateFrom))
            && (workDate.isBefore(dateTo) || workDate.isEqual(dateTo));
    }

    private int calculateMoneyPerHour(String workingHours, String moneyPerHour) {
        return Integer.parseInt(workingHours) * Integer.parseInt(moneyPerHour);
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, TIME_FORMATTER);
    }
}
