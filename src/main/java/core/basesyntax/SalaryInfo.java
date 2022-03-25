package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int amount = 0;
            for (String field : data) {
                if ((getDate(field).isAfter(getDate(dateFrom))
                        || getDate(field).isEqual(getDate(dateFrom)))
                        && (getDate(field).isBefore(getDate(dateTo))
                        || getDate(field).isEqual(getDate(dateTo)))
                        && field.contains(name)) {
                    amount += getEarnedMoney(field);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(amount);
        }
        return stringBuilder.toString();
    }

    public LocalDate getDate(String inputData) {
        String[] items = inputData.split(" ");
        return LocalDate.parse(items[0], FORMATTER);
    }

    public int getEarnedMoney(String inputData) {

        String[] items = inputData.split(" ");
        return Integer.parseInt(items[2]) * Integer.parseInt(items[3]);
    }
}
