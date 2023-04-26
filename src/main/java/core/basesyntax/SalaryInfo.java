package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_PAYMENT_INDEX = 3;

    private int getUserIndex(String name, String[] users) {
        int index = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        int[] totalIncomesForAllUsers = new int[names.length];

        for (String entry : data) {
            String[] userData = entry.split(" ");
            LocalDate entryData = LocalDate.parse(userData[DATE_INDEX], formatter);
            String userName = userData[NAME_INDEX];
            int workingHours = Integer.parseInt(userData[HOURS_INDEX]);
            int hourlyPayment = Integer.parseInt(userData[HOURLY_PAYMENT_INDEX]);
            int userIndex = getUserIndex(userName, names);

            if (entryData.equals(startDate) || entryData.equals(endDate)
                    || entryData.isAfter(startDate) && entryData.isBefore(endDate)) {
                totalIncomesForAllUsers[userIndex] += workingHours * hourlyPayment;
            }
        }

        StringBuilder builder;
        builder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int userIndex = getUserIndex(name, names);
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalIncomesForAllUsers[userIndex]);
        }

        return builder.toString();
    }
}
