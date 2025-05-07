package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int amount = 0;
            for (String field : data) {
                String[] items = field.split(" ");
                if (compareDate(items[0], dateFrom, dateTo) && field.contains(name)) {
                    amount += getEarnedMoney(items[2], items[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(amount);
        }
        return stringBuilder.toString();
    }

    private LocalDate getDate(String inputData) {
        return LocalDate.parse(inputData, FORMATTER);
    }

    private int getEarnedMoney(String inputHours, String inputMoney) {
        return Integer.parseInt(inputHours) * Integer.parseInt(inputMoney);
    }

    private boolean compareDate(String date, String dateFrom, String dateTo) {
        return (getDate(date).isAfter(getDate(dateFrom))
                || getDate(date).isEqual(getDate(dateFrom)))
                && (getDate(date).isBefore(getDate(dateTo))
                || getDate(date).isEqual(getDate(dateTo)));
    }
}
