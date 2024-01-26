package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATES = 0;
    private static final int NAMES = 1;
    private static final int HOURS = 2;
    private static final int RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate startDate;
        LocalDate endDate;
        startDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        endDate = LocalDate.parse(dateTo, dateTimeFormatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalEarned = 0;

            for (String input : data) {
                String[] parts = input.split(" ");
                String inputDate = parts[DATES];
                String inputName = parts[NAMES];
                int hoursWorkedOfDay = Integer.parseInt(parts[HOURS]);
                int rateOfDay = Integer.parseInt(parts[RATE]);

                LocalDate localDate = LocalDate.parse(inputDate, dateTimeFormatter);

                if (inputName.equals(name) && localDate.isAfter(startDate.minusDays(1))
                        && localDate.isBefore(endDate.plusDays(1))) {
                    totalEarned += hoursWorkedOfDay * rateOfDay;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalEarned);
        }
        return builder.toString();
    }
}
