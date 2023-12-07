package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private String dateFrom;
    private String dateTo;
    private int amountOfMoney = 0;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (String info : data) {
                if (names[i].equals(getName(info)) && fitsInTime(info, dateFrom, dateTo)) {
                    amountOfMoney += getMoney(info);
                }
            }
            builder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(amountOfMoney);
            amountOfMoney = 0;
        }
        return builder.toString();
    }

    public LocalDate getDate(String data) {
        String result = data.split(" ")[0];
        return LocalDate.parse(result, formatter);
    }

    public String getName(String data) {
        return String.valueOf(data.split(" ")[1]);
    }

    public int getMoney(String data) {
        String[] divided = data.split(" ");
        int hours = Integer.parseInt(divided[2]);
        int moneyPerHour = Integer.parseInt(divided[3]);
        return hours * moneyPerHour;
    }

    public boolean fitsInTime(String string, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        LocalDate givenDate = getDate(string);
        boolean fits = localDateTo.equals(givenDate)
                || (localDateTo.isAfter(givenDate) && localDateFrom.isBefore(givenDate));
        return fits;
    }
}
