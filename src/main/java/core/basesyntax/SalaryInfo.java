package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(DASH).append(dateTo);
        int amountOfMoney = 0;
        for (int i = 0; i < names.length; i++) {
            for (String info : data) {
                if (names[i].equals(getName(info)) && fitsInTime(info, dateFrom, dateTo)) {
                    amountOfMoney += getMoney(info);
                }
            }
            builder.append(System.lineSeparator())
                    .append(names[i]).append(DASH).append(amountOfMoney);
            amountOfMoney = 0;
        }
        return builder.toString();
    }

    public LocalDate getDate(String data) {
        final int index = 0;
        String result = data.split(" ")[index];
        return LocalDate.parse(result, FORMATTER);
    }

    public String getName(String data) {
        final int index = 1;
        return String.valueOf(data.split(" ")[index]);
    }

    public int getMoney(String data) {
        final int hours_index = 2;
        final int money_per_hour_index = 3;
        String[] divided = data.split(" ");
        int hours = Integer.parseInt(divided[hours_index]);
        int moneyPerHour = Integer.parseInt(divided[money_per_hour_index]);
        return hours * moneyPerHour;
    }

    public boolean fitsInTime(String string, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate givenDate = getDate(string);
        boolean fits = localDateTo.equals(givenDate)
                || (localDateTo.isAfter(givenDate) && localDateFrom.isBefore(givenDate));
        return fits;
    }
}
