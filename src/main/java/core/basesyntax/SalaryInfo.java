package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        final int date = 0;
        final int workingHours = 2;
        final int moneyPerHour = 3;

        LocalDate localDateFrom = LocalDate.parse(dateFrom, timeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, timeFormatter);

        for (String username: names) {
            int money = 0;
            for (String jobData: data) {
                if (jobData.contains(username)) {
                    String[] parts = jobData.split(" ");
                    LocalDate workDate = LocalDate.parse(parts[date],
                                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    if ((workDate.isAfter(localDateFrom) || workDate.isEqual(localDateFrom))
                                && (workDate.isBefore(localDateTo)
                                || workDate.isEqual(localDateTo))) {
                        money += Integer.parseInt(parts[workingHours])
                                * Integer.parseInt(parts[moneyPerHour]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(username).append(" - ").append(money);
        }
        return result.toString();
    }
}
