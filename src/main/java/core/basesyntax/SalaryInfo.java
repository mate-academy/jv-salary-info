package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] currentData = datum.split(" ");
                int hours = Integer.parseInt(currentData[INDEX_HOURS]);
                int income = Integer.parseInt(currentData[INDEX_INCOME]);
                String date = currentData[INDEX_DATE];
                try {
                    if (currentData[INDEX_NAME].equals(name)) {
                        if (getMonth(dateFrom) < getMonth(dateTo)
                                && getMonth(date) == getMonth(dateFrom)
                                && getDay(date) >= getDay(dateFrom)
                                || getMonth(date) == getMonth(dateTo)
                                && getDay(date) <= getDay(dateTo)
                                || getDay(date) >= getDay(dateFrom)
                                && getDay(date) <= getDay(dateTo)) {
                            salary += hours * income;
                        }
                    } else {
                        throw new NoSuchNameException("No such name");
                    }
                } catch (NoSuchNameException e) {
                    continue;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }

    public int getDay(String date) {
        LocalDate localDate = LocalDate.parse(date, FORMATTER);
        return localDate.getDayOfMonth();
    }

    public int getMonth(String date) {
        LocalDate localDate = LocalDate.parse(date, FORMATTER);
        return localDate.getMonthValue();
    }
}
