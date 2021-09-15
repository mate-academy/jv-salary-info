package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name: names) {
            int totalIncome = 0;

            for (String userData : data) {
                String[] userDataParts = userData.split(" ");
                String userDate = userDataParts[0];
                String userName = userDataParts[1];
                int userWorkingHours = Integer.parseInt(userDataParts[2]);
                int ratePerHour = Integer.parseInt(userDataParts[3]);
                LocalDate checkDate = LocalDate.parse(userDate, FORMATTER);

                if (name.equals(userName) && isInDates(checkDate, from, to)) {
                    totalIncome += userWorkingHours * ratePerHour;
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalIncome);
        }
        return builder.toString();
    }

    private boolean isInDates(LocalDate date, LocalDate from, LocalDate to) {
        return (date.isAfter(from)
                && date.isBefore(to)
                || date.isEqual(from)
                || date.isEqual(to));
    }
}
