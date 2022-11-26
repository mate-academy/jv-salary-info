package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE = 0;
    static final int NAME = 1;
    static final int HOURS = 2;
    static final int PRICE = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);
        User[] users = new User[names.length];
        String[] array = data;

        for (int i = 0; i < names.length; i++) {
            users[i] = new User(names[i]);
        }

        LocalDate dynamicDate;

        for (String element: data) {
            dynamicDate = LocalDate.parse(element.substring(0, 10), FORMATTER);
            array = element.split(" ");
            if ((dynamicDate.isAfter(fromDate) && dynamicDate.isBefore(toDate))
                    || (dynamicDate.isEqual(fromDate)) || (dynamicDate.isEqual(toDate))) {
                for (User user: users) {
                    if (array[NAME].equals(user.getName())) {
                        user.addMoney(Integer.parseInt(array[HOURS]),
                                Integer.parseInt(array[PRICE]));
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (User user: users) {
            stringBuilder.append(System.lineSeparator())
                    .append(user.getName())
                    .append(" - ")
                    .append(user.getEarned());
        }
        return stringBuilder.toString();
    }
}
