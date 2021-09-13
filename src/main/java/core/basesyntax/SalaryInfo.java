package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name: names) {
            int totalIncome = 0;

            for (String userData : data) {
                String userName = getName(userData);
                String userDate = getDate(userData);
                int userWorkingHours = getWorkingHours(userData);
                int ratePerHour = getRatePerHour(userData);
                LocalDate checkDate = convertDate(userDate);
                LocalDate from = convertDate(dateFrom);
                LocalDate to = convertDate(dateTo);

                if (name.equals(userName) && isInDates(checkDate, from, to)) {
                    totalIncome += calculateDayIncome(userWorkingHours, ratePerHour);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalIncome);
        }
        return builder.toString();
    }

    private String getDate(String userData) {
        String[] parts = userData.split(" ");
        return parts[0];
    }

    private String getName(String data) {
        String[] parts = data.split(" ");
        return parts[1];
    }

    private int getWorkingHours(String data) {
        String[] parts = data.split(" ");
        return Integer.parseInt(parts[2]);
    }

    private int getRatePerHour(String data) {
        String[] parts = data.split(" ");
        return Integer.parseInt(parts[3]);
    }

    private LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        return LocalDate.parse(date, formatter);
    }

    private boolean isInDates(LocalDate date, LocalDate from, LocalDate to) {
        return (date.isAfter(from)
                && date.isBefore(to)
                || date.isEqual(from)
                || date.isEqual(to));
    }

    private int calculateDayIncome(int workingHours, int incomePerHour) {
        return workingHours * incomePerHour;
    }
}
