package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int DAYS_AMOUNT_POSITION = 2;
    public static final int MONEY_PER_DAY_POSITION = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        String namesAndSalary = "";

        for (String name : names) {
            int money = 0;
            for (int i = 0; i < data.length; i++) {
                String[] temp = data[i].split(" ");
                LocalDate dataOfSalary = LocalDate.parse(temp[DATE_POSITION], formatter);

                if (temp[NAME_POSITION].equals(name)
                        && dataOfSalary.isAfter(from.minusDays(1))
                        && dataOfSalary.isBefore(to.plusDays(1))) {
                    money += Integer.parseInt(temp[DAYS_AMOUNT_POSITION])
                            * Integer.parseInt(temp[MONEY_PER_DAY_POSITION]);
                }
            }
            StringBuilder builder = new StringBuilder();
            namesAndSalary = builder
                    .append(namesAndSalary)
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(money)
                    .toString();
        }
        return "Report for period "
                + dateFrom
                + " - "
                + dateTo
                + namesAndSalary;
    }
}
